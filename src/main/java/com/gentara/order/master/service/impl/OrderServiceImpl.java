package com.gentara.order.master.service.impl;

import com.gentara.order.enums.PaymentStatus;
import com.gentara.order.enums.OrderStatus;
import com.gentara.order.exception.BadRequestException;
import com.gentara.order.exception.PaymentServiceException;
import com.gentara.order.master.model.entity.OrderDetailsEntity;
import com.gentara.order.master.model.entity.OrderEntity;
import com.gentara.order.master.model.entity.ProductEntity;
import com.gentara.order.master.model.request.OrderDetailsReq;
import com.gentara.order.master.model.request.OrderReq;
import com.gentara.order.master.model.response.OrderDetailsRes;
import com.gentara.order.master.model.response.OrderRes;
import com.gentara.order.master.model.response.PaymentRes;
import com.gentara.order.master.repository.OrderRepo;
import com.gentara.order.master.repository.ProductRepo;
import com.gentara.order.master.service.OrderService;
import com.gentara.order.master.service.PaymentService;
import com.gentara.order.master.service.mapper.ServiceMapper;
import com.gentara.order.util.CommonUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final OrderDetailsServiceImpl orderDetailsService;
    private final PaymentService paymentService;
    private final ServiceMapper serviceMapper;

    @Override
    public List<OrderRes> getAll() {
        List<OrderEntity> result = orderRepo.findAll();
        return result.stream().map(this::entityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderRes> getById(String id) {
        return Optional.of(entityToRes(serviceMapper.getOrderEntity(id)));
    }

    @Override
    @Transactional
    public Optional<OrderRes> create(OrderReq orderReq) {
        if (orderReq.getOrderDetails() == null || orderReq.getOrderDetails().isEmpty()) {
            throw new BadRequestException("Order must have at least one order detail");
        }

        List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderDetailsReq detailReq : orderReq.getOrderDetails()) {
            ProductEntity product = serviceMapper.getProductEntity(detailReq.getProductId());

            if (product.getStock() < detailReq.getQuantity()) {
                throw new BadRequestException("Insufficient stock for product: " + product.getName());
            }
            product.setStock(product.getStock() - detailReq.getQuantity());
            productRepo.save(product);

            BigDecimal unitPrice = product.getPrice();
            BigDecimal subTotal = unitPrice.multiply(BigDecimal.valueOf(detailReq.getQuantity()));

            orderDetailsEntities.add(OrderDetailsEntity.builder()
                    .id(CommonUtil.getUUID())
                    .product(product)
                    .quantity(detailReq.getQuantity())
                    .unitPrice(unitPrice)
                    .subtotal(subTotal)
                    .build());

            totalAmount = totalAmount.add(subTotal);
        }

        String orderId = CommonUtil.getUUID();
        OrderEntity orderEntity = OrderEntity.builder()
                .id(orderId)
                .orderNumber("INV-" + orderId.substring(0, 8).toUpperCase())
                .customer(orderReq.getCustomerId() != null ? serviceMapper.getCustomerEntity(orderReq.getCustomerId()) : null)
                .idempotencyKey(CommonUtil.getUUID())
                .orderStatus(OrderStatus.PROCESSING)
                .paymentMethod(orderReq.getPaymentMethod())
                .orderDate(LocalDateTime.now())
                .totalAmount(totalAmount)
                .build();

        orderDetailsEntities.forEach(d -> d.setOrder(orderEntity));
        orderEntity.setOrderDetails(orderDetailsEntities);

        OrderEntity savedOrder = orderRepo.save(orderEntity);

        try {
            PaymentRes paymentRes = paymentService.create(savedOrder);
            if (paymentRes != null) {
                savedOrder.setPaymentNumber(paymentRes.getPaymentNumber());
                savedOrder.setPaymentStatus(paymentRes.getPaymentStatus());
                savedOrder.setExpiredAt(paymentRes.getExpiredAt());
                savedOrder.setOrderStatus(OrderStatus.CREATED);
            }
        } catch (PaymentServiceException e) {
            savedOrder.setOrderStatus(OrderStatus.FAILED);
        }
        return Optional.of(entityToRes(savedOrder));
    }

    @Override
    public Optional<OrderRes> delete(String id) {
        OrderEntity entity = serviceMapper.getOrderEntity(id);

        try {
            this.orderRepo.delete(entity);
            return Optional.of(entityToRes(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderRes> getOrdersByCustomerId(String customerId) {
        return orderRepo.findAll().stream()
                .filter(order -> order.getCustomer() != null && order.getCustomer().getId().equals(customerId))
                .map(this::entityToRes)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderRes> getOrdersByStatus(OrderStatus orderStatus) {
        return orderRepo.findAll().stream()
                .filter(order -> order.getOrderStatus() != null && order.getOrderStatus().equals(orderStatus))
                .map(this::entityToRes)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderRes> getOrdersByPaymentStatus(PaymentStatus paymentStatus) {
        return orderRepo.findAll().stream()
                .filter(order -> order.getPaymentStatus() != null && order.getPaymentStatus().equals(paymentStatus))
                .map(this::entityToRes)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderRes> updateStatus(String id, OrderStatus orderStatus) {
        OrderEntity entity = this.serviceMapper.getOrderEntity(id);
        entity.setOrderStatus(orderStatus);

        try {
            this.orderRepo.save(entity);
            return Optional.of(entityToRes(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<OrderRes> cancelOrder(String id) {
        OrderEntity entity = this.serviceMapper.getOrderEntity(id);
        entity.setOrderStatus(OrderStatus.CANCELLED);

        try {
            this.orderRepo.save(entity);
            return Optional.of(entityToRes(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private OrderRes entityToRes(OrderEntity entity) {
        return OrderRes.builder()
                .id(entity.getId())
                .orderNumber(entity.getOrderNumber())
                .customerId(entity.getCustomer() != null ? entity.getCustomer().getId() : null)
                .orderDate(entity.getOrderDate())
                .orderStatus(entity.getOrderStatus())
                .paymentStatus(entity.getPaymentStatus())
                .idempotencyKey(entity.getIdempotencyKey())
                .totalAmount(entity.getTotalAmount())
                .paymentMethod(entity.getPaymentMethod())
                .paidAt(entity.getPaidAt())
                .createdAt(entity.getCreatedAt())
                .orderDetails(toOrderDetailsRes(entity.getOrderDetails()))
                .build();
    }

    private OrderEntity requestToEntity(OrderReq request) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(CommonUtil.getUUID());
        orderEntity.setOrderNumber("INV-" + CommonUtil.getUUID().substring(0, 8) + "-" + System.currentTimeMillis());
        orderEntity.setCustomer(request.getCustomerId() != null ? serviceMapper.getCustomerEntity(request.getCustomerId()) : null);
        orderEntity.setIdempotencyKey(CommonUtil.getUUID());
        orderEntity.setOrderStatus(OrderStatus.PROCESSING);
        orderEntity.setPaymentMethod(request.getPaymentMethod());

        List<OrderDetailsEntity> orderDetailsEntities = this.toOrderDetailsEntity(request.getOrderDetails());
        BigDecimal totalAmount = new BigDecimal("0");
        for (OrderDetailsEntity orderDetails : orderDetailsEntities) {
            totalAmount = totalAmount.add(orderDetails.getSubtotal());
        }
        try {
            return orderRepo.save(orderEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        return OrderEntity.builder()
//                .id(CommonUtil.getUUID())
//                .orderNumber("INV-" + CommonUtil.getUUID().substring(0, 8) + "-" + System.currentTimeMillis())
//                .customer(request.getCustomerId() != null ? serviceMapper.getCustomerEntity(request.getCustomerId()) : null)
//                .idempotencyKey(CommonUtil.getUUID())
//                .totalAmount(totalAmount)
//                .orderStatus(OrderStatus.PROCESSING)
//                .paymentMethod(request.getPaymentMethod())
//                .orderDetails(orderDetailsEntities)
//                .build();
    }

    private OrderEntity requestToEntity(OrderEntity entity, OrderReq request) {
        entity.setOrderNumber("INV-" + CommonUtil.getUUID().substring(0, 8) + "-" + System.currentTimeMillis());
        entity.setCustomer(request.getCustomerId() != null ? serviceMapper.getCustomerEntity(request.getCustomerId()) : null);
        entity.setOrderDate(request.getOrderDate());
        entity.setOrderStatus(request.getOrderStatus());
        entity.setPaymentStatus(request.getPaymentStatus());
        entity.setTotalAmount(request.getTotalAmount());
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setPaidAt(request.getPaidAt());
        return entity;
    }


    private List<OrderDetailsRes> toOrderDetailsRes(List<OrderDetailsEntity> orderDetails) {
        if (orderDetails == null || orderDetails.isEmpty()) return Collections.emptyList();
        return orderDetails.stream().map(orderDetailsService::mapEntityToResponse).collect(Collectors.toList());
    }

    private List<OrderDetailsEntity> toOrderDetailsEntity(List<OrderDetailsReq> orderDetailsReq) {
        if (orderDetailsReq == null || orderDetailsReq.isEmpty()) return Collections.emptyList();
        return orderDetailsReq.stream().map(orderDetailsService::mapRequestToEntity).collect(Collectors.toList());
    }
}

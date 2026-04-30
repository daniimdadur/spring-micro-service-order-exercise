package com.gentara.order.master.service.impl;

import com.gentara.order.enums.PaymentStatus;
import com.gentara.order.master.model.entity.OrderEntity;
import com.gentara.order.master.model.request.OrderReq;
import com.gentara.order.master.model.response.OrderRes;
import com.gentara.order.master.repository.OrderRepo;
import com.gentara.order.master.service.OrderService;
import com.gentara.order.master.service.mapper.ServiceMapper;
import com.gentara.order.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
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
    public Optional<OrderRes> create(OrderReq orderReq) {
        OrderEntity result = this.requestToEntity(orderReq);

        try {
            orderRepo.save(result);
            return Optional.of(entityToRes(result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<OrderRes> update(String id, OrderReq orderReq) {
        OrderEntity entity = this.requestToEntity(serviceMapper.getOrderEntity(id), orderReq);

        try {
            this.orderRepo.save(entity);
            return Optional.of(entityToRes(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public List<OrderRes> getOrdersByStatus(String status) {
        return orderRepo.findAll().stream()
                .filter(order -> order.getStatus() != null && order.getStatus().equals(status))
                .map(this::entityToRes)
                .collect(Collectors.toList());
    }

    private OrderRes entityToRes(OrderEntity entity) {
        return OrderRes.builder()
                .id(entity.getId())
                .orderNumber(entity.getOrderNumber())
                .customerId(entity.getCustomer() != null ? entity.getCustomer().getId() : null)
                .orderDate(entity.getOrderDate())
                .status(entity.getStatus())
                .paymentStatus(entity.getPaymentStatus())
                .idempotencyKey(entity.getIdempotencyKey())
                .totalAmount(entity.getTotalAmount())
                .paymentMethod(entity.getPaymentMethod())
                .paidAt(entity.getPaidAt())
                .notes(entity.getNotes())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private OrderEntity requestToEntity(OrderReq request) {
        return OrderEntity.builder()
                .id(CommonUtil.getUUID())
                .orderNumber(request.getOrderNumber())
                .customer(request.getCustomerId() != null ? serviceMapper.getCustomerEntity(request.getCustomerId()) : null)
                .orderDate(request.getOrderDate())
                .status(request.getStatus())
                .paymentStatus(request.getPaymentStatus() != null ? request.getPaymentStatus() : PaymentStatus.UNPAID)
                .idempotencyKey(CommonUtil.getUUID())
                .totalAmount(request.getTotalAmount())
                .paymentMethod(request.getPaymentMethod())
                .paidAt(request.getPaidAt())
                .notes(request.getNotes())
                .build();
    }

    private OrderEntity requestToEntity(OrderEntity entity, OrderReq request) {
        entity.setOrderNumber(request.getOrderNumber());
        entity.setCustomer(request.getCustomerId() != null ? serviceMapper.getCustomerEntity(request.getCustomerId()) : null);
        entity.setOrderDate(request.getOrderDate());
        entity.setStatus(request.getStatus());
        entity.setPaymentStatus(request.getPaymentStatus());
        entity.setTotalAmount(request.getTotalAmount());
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setPaidAt(request.getPaidAt());
        entity.setNotes(request.getNotes());
        return entity;
    }
}

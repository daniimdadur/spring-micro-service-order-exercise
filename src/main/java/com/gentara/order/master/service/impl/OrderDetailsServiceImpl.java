package com.gentara.order.master.service.impl;

import com.gentara.order.master.model.entity.OrderDetailsEntity;
import com.gentara.order.master.model.request.OrderDetailsReq;
import com.gentara.order.master.model.response.OrderDetailsRes;
import com.gentara.order.master.repository.OrderDetailsRepo;
import com.gentara.order.master.service.OrderDetailsService;
import com.gentara.order.master.service.mapper.ServiceMapper;
import com.gentara.order.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private final OrderDetailsRepo orderDetailsRepo;
    private final ServiceMapper serviceMapper;

    @Override
    public List<OrderDetailsRes> getAll() {
        return orderDetailsRepo.findAll().stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDetailsRes> getById(String id) {
        return Optional.of(mapEntityToResponse(serviceMapper.getOrderDetailsEntity(id)));
    }

    @Override
    public Optional<OrderDetailsRes> create(OrderDetailsReq orderDetailsReq) {
        OrderDetailsEntity entity = this.mapRequestToEntity(orderDetailsReq);

        try {
            this.orderDetailsRepo.save(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<OrderDetailsRes> update(String id, OrderDetailsReq orderDetailsReq) {
        OrderDetailsEntity entity = this.mapRequestToEntity(serviceMapper.getOrderDetailsEntity(id), orderDetailsReq);

        try {
            this.orderDetailsRepo.save(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<OrderDetailsRes> delete(String id) {
        OrderDetailsEntity entity = serviceMapper.getOrderDetailsEntity(id);

        try {
            this.orderDetailsRepo.delete(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDetailsRes> getOrderDetailsByOrderId(String orderId) {
        return orderDetailsRepo.findAll().stream()
                .filter(orderDetail -> orderDetail.getOrder() != null && orderDetail.getOrder().getId().equals(orderId))
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailsRes> getOrderDetailsByProductId(String productId) {
        return orderDetailsRepo.findAll().stream()
                .filter(orderDetail -> orderDetail.getProduct() != null && orderDetail.getProduct().getId().equals(productId))
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    private OrderDetailsRes mapEntityToResponse(OrderDetailsEntity entity) {
        return OrderDetailsRes.builder()
                .id(entity.getId())
                .orderId(entity.getOrder() != null ? entity.getOrder().getId() : null)
                .productId(entity.getProduct() != null ? entity.getProduct().getId() : null)
                .quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice())
                .subtotal(entity.getSubtotal())
                .build();
    }

    private OrderDetailsEntity mapRequestToEntity(OrderDetailsReq req) {
        return OrderDetailsEntity.builder()
                .id(CommonUtil.getUUID())
                .order(req.getOrderId() != null ? serviceMapper.getOrderEntity(req.getOrderId()) : null)
                .product(req.getProductId() != null ? serviceMapper.getProductEntity(req.getProductId()) : null)
                .quantity(req.getQuantity())
                .unitPrice(req.getUnitPrice())
                .subtotal(req.getSubtotal())
                .build();
    }

    private OrderDetailsEntity mapRequestToEntity(OrderDetailsEntity entity, OrderDetailsReq req) {
        entity.setOrder(req.getOrderId() != null ? serviceMapper.getOrderEntity(req.getOrderId()) : null);
        entity.setProduct(req.getProductId() != null ? serviceMapper.getProductEntity(req.getProductId()) : null);
        entity.setQuantity(req.getQuantity());
        entity.setUnitPrice(req.getUnitPrice());
        entity.setSubtotal(req.getSubtotal());
        return entity;
    }
}

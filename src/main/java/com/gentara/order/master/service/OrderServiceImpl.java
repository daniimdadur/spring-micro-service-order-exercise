package com.gentara.order.master.service;

import com.gentara.order.enums.PaymentStatus;
import com.gentara.order.master.client.PaymentClient;
import com.gentara.order.master.model.entity.OrderEntity;
import com.gentara.order.master.model.request.OrderReq;
import com.gentara.order.master.model.response.OrderRes;
import com.gentara.order.master.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepo orderRepo;
    private final PaymentClient paymentClient;

    @Override
    public List<OrderRes> getAll() {
        List<OrderEntity> result = orderRepo.findAll();
        return result.stream().map(this::entityToRes).toList();
    }

    @Override
    public Optional<OrderRes> getById(String id) {
        return Optional.empty();
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
        return Optional.empty();
    }

    @Override
    public Optional<OrderRes> delete(String id) {
        return Optional.empty();
    }

    private OrderRes entityToRes(OrderEntity entity) {
        return OrderRes.builder()
                .id(entity.getId())
                .productName(entity.getProductName())
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .build();
    }

    private OrderEntity requestToEntity(OrderReq request) {
        return OrderEntity.builder()
                .productName(request.getProductName())
                .amount(request.getAmount())
                .status(PaymentStatus.UNPAID)
                .build();
    }

    private OrderEntity requestToEntity(OrderEntity entity, OrderReq request) {
        entity.setProductName(request.getProductName());
        entity.setAmount(request.getAmount());
        return entity;
    }
}

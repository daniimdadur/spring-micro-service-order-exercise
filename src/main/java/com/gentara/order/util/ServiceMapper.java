package com.gentara.order.util;

import com.gentara.order.exception.NotFoundException;
import com.gentara.order.master.model.entity.OrderEntity;
import com.gentara.order.master.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceMapper {
    private final OrderRepo orderRepo;

    public OrderEntity getOrderEntity(String id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("order with id %s not found", id)));
    }
}

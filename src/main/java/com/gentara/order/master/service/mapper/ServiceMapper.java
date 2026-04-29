package com.gentara.order.master.service.mapper;

import com.gentara.order.exception.NotFoundException;
import com.gentara.order.master.model.entity.*;
import com.gentara.order.master.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceMapper {
    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final OrderDetailsRepo orderDetailsRepo;

    public OrderEntity getOrderEntity(String id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("order with id %s not found", id)));
    }

    public CustomerEntity getCustomerEntity(String id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("customer with id %s not found", id)));
    }

    public CategoryEntity getCategoryEntity(String id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("category with id %s not found", id)));
    }

    public ProductEntity getProductEntity(String id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("product with id %s not found", id)));
    }

    public OrderDetailsEntity getOrderDetailsEntity(String id) {
        return orderDetailsRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("order details with id %s not found", id)));
    }
}

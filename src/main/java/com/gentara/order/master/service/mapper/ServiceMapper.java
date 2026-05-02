package com.gentara.order.master.service.mapper;

import com.gentara.order.exception.NotFoundException;
import com.gentara.order.master.model.entity.*;
import com.gentara.order.master.repository.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
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

    public <T> T getEntityById(JpaRepository<@NonNull T, @NonNull String> repo, String id) {
        String entityName = repo.getClass()
                .getInterfaces()[0]
                .getSimpleName()
                .replace("Repo", "")
                .toLowerCase();

        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("%s with id %s not found", entityName, id)));
    }

    public CustomerEntity getCustomerEntityByEmail(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("customer with email %s not found", email)));
    }

    public CustomerEntity getCustomerEntityByName(String name) {
        return customerRepo.findByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("customer with name %s not found", name)));
    }

    public ProductEntity getProductEntityBySku(String sku) {
        return productRepo.findBySku(sku)
                .orElseThrow(() -> new NotFoundException(String.format("product with sku %s not found", sku)));
    }

    public ProductEntity getProductEntityByName(String name) {
        return productRepo.findByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("product with name %s not found", name)));
    }
}

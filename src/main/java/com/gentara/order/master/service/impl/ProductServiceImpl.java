package com.gentara.order.master.service.impl;

import com.gentara.order.master.model.entity.ProductEntity;
import com.gentara.order.master.model.request.ProductReq;
import com.gentara.order.master.model.response.ProductRes;
import com.gentara.order.master.repository.ProductRepo;
import com.gentara.order.master.service.ProductService;
import com.gentara.order.master.service.mapper.ServiceMapper;
import com.gentara.order.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ServiceMapper serviceMapper;

    @Override
    public List<ProductRes> getAll() {
        return productRepo.findAll().stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRes> getById(String id) {
        return Optional.of(mapEntityToResponse(serviceMapper.getProductEntity(id)));
    }

    @Override
    public Optional<ProductRes> create(ProductReq productReq) {
        ProductEntity entity = this.mapRequestToEntity(productReq);

        try {
            this.productRepo.save(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ProductRes> update(String id, ProductReq productReq) {
        ProductEntity entity = this.mapRequestToEntity(serviceMapper.getProductEntity(id), productReq);

        try {
            this.productRepo.save(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ProductRes> delete(String id) {
        ProductEntity entity = serviceMapper.getProductEntity(id);

        try {
            this.productRepo.delete(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductRes> getProductsByCategoryId(String categoryId) {
        return productRepo.findByCategory_id(categoryId)
                .stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRes> getProductBySku(String sku) {
        return Optional.of(serviceMapper.getProductEntityBySku(sku))
                .map(this::mapEntityToResponse);
    }

    @Override
    public Optional<ProductRes> getProductByName(String name) {
        return Optional.of(serviceMapper.getProductEntityByName(name))
                .map(this::mapEntityToResponse);
    }

    private ProductRes mapEntityToResponse(ProductEntity entity) {
        return ProductRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .sku(entity.getSku())
                .categoryId(entity.getCategory() != null ? entity.getCategory().getId() : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private ProductEntity mapRequestToEntity(ProductReq req) {
        return ProductEntity.builder()
                .id(CommonUtil.getUUID())
                .name(req.getName())
                .description(req.getDescription())
                .price(req.getPrice())
                .stock(req.getStock())
                .sku(req.getSku())
                .category(req.getCategoryId() != null ? serviceMapper.getCategoryEntity(req.getCategoryId()) : null)
                .build();
    }

    private ProductEntity mapRequestToEntity(ProductEntity entity, ProductReq req) {
        entity.setName(req.getName());
        entity.setDescription(req.getDescription());
        entity.setPrice(req.getPrice());
        entity.setStock(req.getStock());
        entity.setSku(req.getSku());
        entity.setCategory(req.getCategoryId() != null ? serviceMapper.getCategoryEntity(req.getCategoryId()) : null);
        return entity;
    }
}

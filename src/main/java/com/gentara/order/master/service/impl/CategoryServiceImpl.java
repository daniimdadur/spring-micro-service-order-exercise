package com.gentara.order.master.service.impl;

import com.gentara.order.master.model.entity.CategoryEntity;
import com.gentara.order.master.model.request.CategoryReq;
import com.gentara.order.master.model.response.CategoryRes;
import com.gentara.order.master.repository.CategoryRepo;
import com.gentara.order.master.service.CategoryService;
import com.gentara.order.master.service.mapper.ServiceMapper;
import com.gentara.order.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ServiceMapper serviceMapper;

    @Override
    public List<CategoryRes> getAll() {
        return categoryRepo.findAll().stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryRes> getById(String id) {
        return Optional.of(mapEntityToResponse(serviceMapper.getCategoryEntity(id)));
    }

    @Override
    public Optional<CategoryRes> create(CategoryReq categoryReq) {
        CategoryEntity entity = this.mapRequestToEntity(categoryReq);

        try {
            this.categoryRepo.save(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CategoryRes> update(String id, CategoryReq categoryReq) {
        CategoryEntity entity = this.mapRequestToEntity(serviceMapper.getCategoryEntity(id), categoryReq);

        try {
            this.categoryRepo.save(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CategoryRes> delete(String id) {
        CategoryEntity entity = serviceMapper.getCategoryEntity(id);

        try {
            this.categoryRepo.delete(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CategoryRes mapEntityToResponse(CategoryEntity entity) {
        return CategoryRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private CategoryEntity mapRequestToEntity(CategoryReq req) {
        return CategoryEntity.builder()
                .id(CommonUtil.getUUID())
                .name(req.getName())
                .description(req.getDescription())
                .build();
    }

    private CategoryEntity mapRequestToEntity(CategoryEntity entity, CategoryReq req) {
        entity.setName(req.getName());
        entity.setDescription(req.getDescription());
        return entity;
    }
}

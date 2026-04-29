package com.gentara.order.master.service.impl;

import com.gentara.order.master.model.entity.CustomerEntity;
import com.gentara.order.master.model.request.CustomerReq;
import com.gentara.order.master.model.response.CustomerRes;
import com.gentara.order.master.repository.CustomerRepo;
import com.gentara.order.master.service.CustomerService;
import com.gentara.order.master.service.mapper.ServiceMapper;
import com.gentara.order.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final ServiceMapper serviceMapper;

    @Override
    public List<CustomerRes> getAll() {
        return customerRepo.findAll().stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerRes> getById(String id) {
        return Optional.of(mapEntityToResponse(serviceMapper.getCustomerEntity(id)));
    }

    @Override
    public Optional<CustomerRes> create(CustomerReq customerReq) {
        CustomerEntity entity = this.mapRequestToEntity(customerReq);

        try {
            this.customerRepo.save(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CustomerRes> update(String id, CustomerReq customerReq) {
        CustomerEntity entity = this.mapRequestToEntity(serviceMapper.getCustomerEntity(id), customerReq);

        try {
            this.customerRepo.save(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CustomerRes> delete(String id) {
        CustomerEntity entity = serviceMapper.getCustomerEntity(id);

        try {
            this.customerRepo.delete(entity);
            return Optional.of(mapEntityToResponse(entity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CustomerRes mapEntityToResponse(CustomerEntity entity) {
        return CustomerRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private CustomerEntity mapRequestToEntity(CustomerReq req) {
        return CustomerEntity.builder()
                .id(CommonUtil.getUUID())
                .name(req.getName())
                .email(req.getEmail())
                .phone(req.getPhone())
                .address(req.getAddress())
                .build();
    }

    private CustomerEntity mapRequestToEntity(CustomerEntity entity, CustomerReq req) {
        entity.setName(req.getName());
        entity.setEmail(req.getEmail());
        entity.setPhone(req.getPhone());
        entity.setAddress(req.getAddress());
        return entity;
    }
}
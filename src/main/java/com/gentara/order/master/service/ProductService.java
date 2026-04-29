package com.gentara.order.master.service;

import com.gentara.order.master.model.request.ProductReq;
import com.gentara.order.master.model.response.ProductRes;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductRes> getAll();
    Optional<ProductRes> getById(String id);
    Optional<ProductRes> create(ProductReq productReq);
    Optional<ProductRes> update(String id, ProductReq productReq);
    Optional<ProductRes> delete(String id);
    List<ProductRes> getProductsByCategoryId(String categoryId);
}

package com.gentara.order.master.service;

import com.gentara.order.master.model.request.CategoryReq;
import com.gentara.order.master.model.response.CategoryRes;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryRes> getAll();
    Optional<CategoryRes> getById(String id);
    Optional<CategoryRes> create(CategoryReq categoryReq);
    Optional<CategoryRes> update(String id, CategoryReq categoryReq);
    Optional<CategoryRes> delete(String id);
}

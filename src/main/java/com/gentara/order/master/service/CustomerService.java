package com.gentara.order.master.service;

import com.gentara.order.master.model.request.CustomerReq;
import com.gentara.order.master.model.response.CustomerRes;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerRes> getAll();
    Optional<CustomerRes> getById(String id);
    Optional<CustomerRes> create(CustomerReq customerReq);
    Optional<CustomerRes> update(String id, CustomerReq customerReq);
    Optional<CustomerRes> delete(String id);
    Optional<CustomerRes> getByEmail(String email);
    Optional<CustomerRes> getByName(String name);
}

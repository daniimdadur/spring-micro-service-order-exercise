package com.gentara.order.master.service;

import com.gentara.order.master.model.request.OrderReq;
import com.gentara.order.master.model.response.OrderRes;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderRes> getAll();
    Optional<OrderRes> getById(String id);
    Optional<OrderRes> create(OrderReq orderReq);
    Optional<OrderRes> update(String id, OrderReq orderReq);
    Optional<OrderRes> delete(String id);
    List<OrderRes> getOrdersByCustomerId(String customerId);
    List<OrderRes> getOrdersByStatus(String status);
}

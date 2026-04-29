package com.gentara.order.master.service;

import com.gentara.order.master.model.request.OrderDetailsReq;
import com.gentara.order.master.model.response.OrderDetailsRes;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsService {
    List<OrderDetailsRes> getAll();
    Optional<OrderDetailsRes> getById(String id);
    Optional<OrderDetailsRes> create(OrderDetailsReq orderDetailsReq);
    Optional<OrderDetailsRes> update(String id, OrderDetailsReq orderDetailsReq);
    Optional<OrderDetailsRes> delete(String id);
    List<OrderDetailsRes> getOrderDetailsByOrderId(String orderId);
    List<OrderDetailsRes> getOrderDetailsByProductId(String productId);
}

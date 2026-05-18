package com.gentara.order.master.service;

import com.gentara.order.enums.OrderStatus;
import com.gentara.order.enums.PaymentStatus;
import com.gentara.order.master.model.request.OrderReq;
import com.gentara.order.master.model.response.OrderRes;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderRes> getAll();
    Optional<OrderRes> getById(String id);
    Optional<OrderRes> create(OrderReq orderReq, String idempotencyKey);
    Optional<OrderRes> delete(String id);
    List<OrderRes> getOrdersByCustomerId(String customerId);
    List<OrderRes> getOrdersByStatus(OrderStatus orderStatus);
    List<OrderRes> getOrdersByPaymentStatus(PaymentStatus paymentStatus);
    Optional<OrderRes> updateStatus(String id, OrderStatus orderStatus);
    Optional<OrderRes> cancelOrder(String id);

}

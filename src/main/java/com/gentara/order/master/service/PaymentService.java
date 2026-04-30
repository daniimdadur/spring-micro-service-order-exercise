package com.gentara.order.master.service;

import com.gentara.order.master.model.response.PaymentRes;

import java.util.Optional;

public interface PaymentService {
    PaymentRes pay(String orderId);
}

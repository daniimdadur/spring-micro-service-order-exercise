package com.gentara.order.master.service;

import com.gentara.order.master.model.entity.OrderEntity;
import com.gentara.order.master.model.response.PaymentRes;

public interface PaymentService {
    PaymentRes create(OrderEntity orderEntity);
}

package com.gentara.order.master.service.impl;

import com.gentara.order.base.Response;
import com.gentara.order.master.client.PaymentClient;
import com.gentara.order.master.model.entity.OrderEntity;
import com.gentara.order.master.model.request.PaymentReq;
import com.gentara.order.master.model.response.PaymentRes;
import com.gentara.order.master.service.PaymentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentClient paymentClient;

    @Override
    public PaymentRes create(OrderEntity orderEntity) {
        try {
            ResponseEntity<@NonNull Response<PaymentRes>> response = paymentClient.payment(mapOrderEntityToPaymentReq(orderEntity));
            assert response.getBody() != null;
            return response.getBody().data();
        } catch (Exception e) {
            throw new RuntimeException("Payment creation failed", e);
        }
    }

    private PaymentReq mapOrderEntityToPaymentReq(OrderEntity orderEntity) {
        return PaymentReq.builder()
                .orderId(orderEntity.getId())
                .orderNumber(orderEntity.getOrderNumber())
                .customerId(orderEntity.getCustomer().getId())
                .amount(orderEntity.getTotalAmount())
                .paymentMethod(orderEntity.getPaymentMethod())
                .idempotencyKey(orderEntity.getIdempotencyKey())
                .build();
    }
}

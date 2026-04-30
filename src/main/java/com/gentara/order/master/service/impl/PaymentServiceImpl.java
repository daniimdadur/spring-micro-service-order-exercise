package com.gentara.order.master.service.impl;

import com.gentara.order.base.Response;
import com.gentara.order.enums.PaymentStatus;
import com.gentara.order.enums.Status;
import com.gentara.order.exception.BadRequestException;
import com.gentara.order.master.client.PaymentClient;
import com.gentara.order.master.model.entity.OrderEntity;
import com.gentara.order.master.model.request.PaymentReq;
import com.gentara.order.master.model.response.PaymentRes;
import com.gentara.order.master.repository.OrderRepo;
import com.gentara.order.master.service.PaymentService;
import com.gentara.order.master.service.mapper.ServiceMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentClient paymentClient;
    private final OrderRepo orderRepo;
    private final ServiceMapper serviceMapper;

    @Override
    public PaymentRes pay(String orderId) {
        OrderEntity orderEntity = serviceMapper.getOrderEntity(orderId);
        if (!PaymentStatus.UNPAID.equals(orderEntity.getPaymentStatus())) {
            throw new BadRequestException("Order already paid or invalid status");
        }

        try {
            ResponseEntity<@NonNull Response<PaymentRes>> response = paymentClient.pay(mapOrderEntityToPaymentReq(orderEntity));
            assert response.getBody() != null;
            if (response.getBody().status() == 200) {
                orderEntity.setPaymentStatus(PaymentStatus.PAID);
                orderEntity.setPaidAt(response.getBody().data().getPaidAt());
                orderRepo.save(orderEntity);
            }
            return response.getBody().data();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
                .callBackUrl("test")
                .notes(orderEntity.getNotes())
                .build();
    }
}

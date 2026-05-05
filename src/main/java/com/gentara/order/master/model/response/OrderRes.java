package com.gentara.order.master.model.response;

import com.gentara.order.enums.PaymentMethod;
import com.gentara.order.enums.PaymentStatus;
import com.gentara.order.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRes {
    private String id;
    private String orderNumber;
    private String customerId;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private String idempotencyKey;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime paidAt;
    private String notes;
    private LocalDateTime createdAt;
    private List<OrderDetailsRes> orderDetails;

    public OrderRes(String id, String orderNumber, String customerId, LocalDateTime orderDate, OrderStatus orderStatus, PaymentStatus paymentStatus, String idempotencyKey, BigDecimal totalAmount, PaymentMethod paymentMethod, LocalDateTime paidAt, String notes, LocalDateTime createdAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.idempotencyKey = idempotencyKey;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paidAt = paidAt;
        this.notes = notes;
        this.createdAt = createdAt;
    }
}

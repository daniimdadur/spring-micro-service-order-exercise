package com.gentara.order.master.model.response;

import com.gentara.order.enums.PaymentMethod;
import com.gentara.order.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private String status;
    private PaymentStatus paymentStatus;
    private String idempotencyKey;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime paidAt;
    private String notes;
    private LocalDateTime createdAt;
}

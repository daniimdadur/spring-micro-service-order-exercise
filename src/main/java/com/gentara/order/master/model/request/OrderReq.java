package com.gentara.order.master.model.request;

import com.gentara.order.enums.PaymentStatus;
import com.gentara.order.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderReq {
    private String orderNumber;
    private String customerId;
    private LocalDateTime orderDate;
    private String status;
    private PaymentStatus paymentStatus;
    private String paymentId;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime paidAt;
    private String notes;
}

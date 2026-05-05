package com.gentara.order.master.model.response;

import com.gentara.order.enums.PaymentMethod;
import com.gentara.order.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRes {
    private String id;
    private String paymentNumber;
    private String orderId;
    private String orderNumber;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
}

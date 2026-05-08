package com.gentara.order.master.model.request;

import com.gentara.order.enums.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentCallbackReq {
    private String orderNumber;
    private String paymentNumber;
    private PaymentStatus paymentStatus;
    private LocalDateTime paidAt;
}

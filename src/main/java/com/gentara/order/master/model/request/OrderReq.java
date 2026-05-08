package com.gentara.order.master.model.request;

import com.gentara.order.enums.OrderStatus;
import com.gentara.order.enums.PaymentStatus;
import com.gentara.order.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderReq {
    private String customerId;
    private PaymentMethod paymentMethod;
    private List<OrderDetailsReq> orderDetails;
}

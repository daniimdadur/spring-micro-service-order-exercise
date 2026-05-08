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
    private String customerName;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private String idempotencyKey;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;
    private List<OrderDetailsRes> orderDetails;
}

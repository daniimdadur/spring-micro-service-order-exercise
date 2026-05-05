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
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime paidAt;
    private List<OrderDetailsReq> orderDetails;

    public OrderReq(String customerId, LocalDateTime orderDate, OrderStatus orderStatus, PaymentStatus paymentStatus, BigDecimal totalAmount, PaymentMethod paymentMethod, LocalDateTime paidAt) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paidAt = paidAt;
    }
}

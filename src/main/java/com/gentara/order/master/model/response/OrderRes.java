package com.gentara.order.master.model.response;

import com.gentara.order.enums.PaymentStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRes {
    private String id;
    private String productName;
    private Integer amount;
    private PaymentStatus status;
}

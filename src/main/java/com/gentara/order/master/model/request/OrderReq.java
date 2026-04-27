package com.gentara.order.master.model.request;

import com.gentara.order.enums.PaymentStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderReq {
    private String productName;
    private Integer amount;
}

package com.gentara.order.master.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerReq {
    private String name;
    private String email;
    private String phone;
    private String address;
}

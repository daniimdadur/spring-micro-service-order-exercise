package com.gentara.order.master.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryReq {
    private String name;
    private String description;
}

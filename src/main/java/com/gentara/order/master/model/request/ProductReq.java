package com.gentara.order.master.model.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReq {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String sku;
    private String categoryId;
}

package com.gentara.order.master.model.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRes {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String sku;
    private String categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

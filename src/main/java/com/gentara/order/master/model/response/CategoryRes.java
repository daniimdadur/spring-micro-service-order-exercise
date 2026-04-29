package com.gentara.order.master.model.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRes {
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}

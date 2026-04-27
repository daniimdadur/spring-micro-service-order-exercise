package com.gentara.order.master.model.entity;

import com.gentara.order.base.BaseAuditableSoftDelete;
import com.gentara.order.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE t_order SET deleted_at=CURRENT_TIMESTAMP WHERE id=?")
@Table(name = "t_order")
public class OrderEntity extends BaseAuditableSoftDelete {

    @Id
    @Column
    private String id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}

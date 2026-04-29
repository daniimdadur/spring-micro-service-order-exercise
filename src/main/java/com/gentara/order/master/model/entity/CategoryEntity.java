package com.gentara.order.master.model.entity;

import com.gentara.order.base.BaseAuditableSoftDelete;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE t_category SET deleted_at=CURRENT_TIMESTAMP WHERE id=?")
@Table(name = "t_category")
public class CategoryEntity extends BaseAuditableSoftDelete {

    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductEntity> products;
}

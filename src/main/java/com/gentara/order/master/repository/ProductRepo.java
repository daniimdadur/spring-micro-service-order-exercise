package com.gentara.order.master.repository;

import com.gentara.order.master.model.entity.ProductEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<@NonNull ProductEntity, @NonNull String> {
    Optional<ProductEntity> findBySku(String sku);
    Optional<ProductEntity> findByName(String name);
    List<ProductEntity> findByCategory_id(String categoryId);
}

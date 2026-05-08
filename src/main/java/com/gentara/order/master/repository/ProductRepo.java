package com.gentara.order.master.repository;

import com.gentara.order.master.model.entity.ProductEntity;
import jakarta.persistence.LockModeType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<@NonNull ProductEntity, @NonNull String> {
    Optional<ProductEntity> findBySku(String sku);
    Optional<ProductEntity> findByName(String name);
    List<ProductEntity> findByCategory_id(String categoryId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM ProductEntity p WHERE p.id = :id")
    Optional<ProductEntity> findByIdWithLock(@Param("id") String id);
}

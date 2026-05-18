package com.gentara.order.master.repository;

import com.gentara.order.master.model.entity.OrderEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<@NonNull OrderEntity, @NonNull String> {
    Optional<OrderEntity> findByIdempotencyKey(String idempotencyKey);
}

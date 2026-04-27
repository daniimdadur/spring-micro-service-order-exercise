package com.gentara.order.master.repository;

import com.gentara.order.master.model.entity.OrderEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<@NonNull OrderEntity, @NonNull String> {
}

package com.gentara.order.master.repository;

import com.gentara.order.master.model.entity.OrderDetailsEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepo extends JpaRepository<@NonNull OrderDetailsEntity, @NonNull String> {
}

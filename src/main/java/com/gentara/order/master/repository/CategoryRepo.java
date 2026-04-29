package com.gentara.order.master.repository;

import com.gentara.order.master.model.entity.CategoryEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<@NonNull CategoryEntity, @NonNull String> {
}

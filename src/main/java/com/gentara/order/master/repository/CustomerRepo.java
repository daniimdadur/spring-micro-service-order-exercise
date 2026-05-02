package com.gentara.order.master.repository;

import com.gentara.order.master.model.entity.CustomerEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<@NonNull CustomerEntity, @NonNull String> {
    Optional<CustomerEntity> findByEmail(String email);
    Optional<CustomerEntity> findByName(String name);
}

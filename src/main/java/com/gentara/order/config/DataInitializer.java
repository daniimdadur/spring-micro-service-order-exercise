package com.gentara.order.config;

import com.gentara.order.master.model.entity.OrderEntity;
import com.gentara.order.master.repository.OrderRepo;
import com.gentara.order.enums.PaymentStatus;
import com.gentara.order.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final OrderRepo orderRepo;
    
    @Override
    public void run(String... args) throws Exception {
        initData();
    }
    
    private void initData() {
        if (orderRepo.count() != 0) return;
        
        List<OrderEntity> orders = List.of(
            OrderEntity.builder()
                .id("a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6")
                .productName("Laptop")
                .amount(1500)
                .status(PaymentStatus.PAID)
                .build(),
            OrderEntity.builder()
                .id("b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7")
                .productName("Phone")
                .amount(800)
                .status(PaymentStatus.UNPAID)
                .build(),
            OrderEntity.builder()
                .id("c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8")
                .productName("Tablet")
                .amount(500)
                .status(PaymentStatus.PARTIAL)
                .build(),
            OrderEntity.builder()
                .id("d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9")
                .productName("Headphones")
                .amount(200)
                .status(PaymentStatus.PAID)
                .build(),
            OrderEntity.builder()
                .id("e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9t0")
                .productName("Keyboard")
                .amount(100)
                .status(PaymentStatus.OVERDUE)
                .build()
        );
        
        try {
            orderRepo.saveAll(orders);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

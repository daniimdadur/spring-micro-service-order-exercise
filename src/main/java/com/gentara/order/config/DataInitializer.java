package com.gentara.order.config;

import com.gentara.order.master.model.entity.*;
import com.gentara.order.master.repository.*;
import com.gentara.order.enums.PaymentStatus;
import com.gentara.order.enums.PaymentMethod;
import com.gentara.order.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final CustomerRepo customerRepo;
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final OrderDetailsRepo orderDetailsRepo;
    
    @Override
    public void run(String... args) throws Exception {
        initData();
    }
    
    private void initData() {
        if (!customerRepo.findAll().isEmpty()) return;
        
        // Initialize Customers (3 data)
        List<CustomerEntity> customers = List.of(
            CustomerEntity.builder()
                .id(CommonUtil.getUUID())
                .name("John Doe")
                .email("john.doe@example.com")
                .phone("08123456789")
                .address("Jl. Merdeka No. 1, Jakarta")
                .build(),
            CustomerEntity.builder()
                .id(CommonUtil.getUUID())
                .name("Jane Smith")
                .email("jane.smith@example.com")
                .phone("08234567890")
                .address("Jl. Sudirman No. 50, Jakarta")
                .build(),
            CustomerEntity.builder()
                .id(CommonUtil.getUUID())
                .name("Michael Johnson")
                .email("michael.j@example.com")
                .phone("08345678901")
                .address("Jl. Ahmad Yani No. 20, Bandung")
                .build()
        );
        customerRepo.saveAll(customers);
        
        // Initialize Categories (3 data)
        List<CategoryEntity> categories = List.of(
            CategoryEntity.builder()
                .id(CommonUtil.getUUID())
                .name("Electronics")
                .description("Electronic items and gadgets")
                .build(),
            CategoryEntity.builder()
                .id(CommonUtil.getUUID())
                .name("Clothing")
                .description("Apparel and fashion items")
                .build(),
            CategoryEntity.builder()
                .id(CommonUtil.getUUID())
                .name("Home & Kitchen")
                .description("Home appliances and kitchen equipment")
                .build()
        );
        categoryRepo.saveAll(categories);
        
        // Initialize Products (3 data)
        List<CategoryEntity> categoryList = categoryRepo.findAll();
        List<ProductEntity> products = List.of(
            ProductEntity.builder()
                .id(CommonUtil.getUUID())
                .name("Dell XPS 13")
                .description("High performance laptop for professionals")
                .price(new BigDecimal("1500.00"))
                .stock(10)
                .sku("DELL-XPS-13")
                .category(categoryList.get(0))
                .build(),
            ProductEntity.builder()
                .id(CommonUtil.getUUID())
                .name("iPhone 15 Pro")
                .description("Latest Apple smartphone with A17 Pro chip")
                .price(new BigDecimal("999.99"))
                .stock(25)
                .sku("IPHONE-15-PRO")
                .category(categoryList.get(0))
                .build(),
            ProductEntity.builder()
                .id(CommonUtil.getUUID())
                .name("Cotton T-Shirt")
                .description("100% organic cotton comfortable t-shirt")
                .price(new BigDecimal("29.99"))
                .stock(100)
                .sku("COTTON-TSHIRT")
                .category(categoryList.get(1))
                .build()
        );
        productRepo.saveAll(products);
        
        // Initialize Orders (3 data)
        List<ProductEntity> productList = productRepo.findAll();
        List<OrderEntity> orders = List.of(
            OrderEntity.builder()
                .id(CommonUtil.getUUID())
                .orderNumber("ORD-001")
                .customer(customers.get(0))
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .paymentStatus(PaymentStatus.UNPAID)
                .totalAmount(new BigDecimal("2499.99"))
                .paymentMethod(PaymentMethod.TRANSFER)
                .notes("Urgent delivery requested")
                .build(),
            OrderEntity.builder()
                .id(CommonUtil.getUUID())
                .orderNumber("ORD-002")
                .customer(customers.get(1))
                .orderDate(LocalDateTime.now())
                .status("CONFIRMED")
                .paymentStatus(PaymentStatus.PAID)
                .paymentId("PAY-12345")
                .totalAmount(new BigDecimal("1029.98"))
                .paymentMethod(PaymentMethod.TRANSFER)
                .paidAt(LocalDateTime.now())
                .notes("Payment confirmed via bank transfer")
                .build(),
            OrderEntity.builder()
                .id(CommonUtil.getUUID())
                .orderNumber("ORD-003")
                .customer(customers.get(2))
                .orderDate(LocalDateTime.now())
                .status("SHIPPED")
                .paymentStatus(PaymentStatus.PARTIAL)
                .paymentId("PAY-67890")
                .totalAmount(new BigDecimal("59.98"))
                .paymentMethod(PaymentMethod.OTHER)
                .paidAt(LocalDateTime.now())
                .notes("Order shipped to Bandung")
                .build()
        );
        orderRepo.saveAll(orders);
        
        // Initialize Order Details (3 data)
        List<OrderEntity> orderList = orderRepo.findAll();
        List<OrderDetailsEntity> orderDetails = List.of(
            OrderDetailsEntity.builder()
                .id(CommonUtil.getUUID())
                .order(orderList.get(0))
                .product(productList.get(0))
                .quantity(1)
                .unitPrice(new BigDecimal("1500.00"))
                .subtotal(new BigDecimal("1500.00"))
                .build(),
            OrderDetailsEntity.builder()
                .id(CommonUtil.getUUID())
                .order(orderList.get(1))
                .product(productList.get(1))
                .quantity(1)
                .unitPrice(new BigDecimal("999.99"))
                .subtotal(new BigDecimal("999.99"))
                .build(),
            OrderDetailsEntity.builder()
                .id(CommonUtil.getUUID())
                .order(orderList.get(2))
                .product(productList.get(2))
                .quantity(2)
                .unitPrice(new BigDecimal("29.99"))
                .subtotal(new BigDecimal("59.98"))
                .build()
        );
        orderDetailsRepo.saveAll(orderDetails);
    }
}

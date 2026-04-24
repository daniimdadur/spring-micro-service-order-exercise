package com.gentara.order.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/orders")
public class ControllerApiTest {
    @GetMapping
    public String getOrders() {
        return "Hello World";
    }
}

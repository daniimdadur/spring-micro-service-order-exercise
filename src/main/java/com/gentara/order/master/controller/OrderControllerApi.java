package com.gentara.order.master.controller;

import com.gentara.order.base.BaseController;
import com.gentara.order.base.Response;
import com.gentara.order.master.model.request.OrderReq;
import com.gentara.order.master.model.response.OrderRes;
import com.gentara.order.master.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/order")
public class OrderControllerApi extends BaseController<OrderRes> {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        return super.getResponse(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable String id) {
        return super.getResponse(orderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody OrderReq orderReq) {
        return super.getResponse(orderService.create(orderReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable String id, @RequestBody OrderReq orderReq) {
        return super.getResponse(orderService.update(id, orderReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        return super.getResponse(orderService.delete(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Response> getOrdersByCustomer(@PathVariable String customerId) {
        return super.getResponse(orderService.getOrdersByCustomerId(customerId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Response> getOrdersByStatus(@PathVariable String status) {
        return super.getResponse(orderService.getOrdersByStatus(status));
    }
}

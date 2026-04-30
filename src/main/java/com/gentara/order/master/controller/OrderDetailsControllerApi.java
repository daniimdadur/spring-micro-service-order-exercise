package com.gentara.order.master.controller;

import com.gentara.order.base.BaseController;
import com.gentara.order.base.Response;
import com.gentara.order.master.model.request.OrderDetailsReq;
import com.gentara.order.master.model.response.OrderDetailsRes;
import com.gentara.order.master.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/order-details")
public class OrderDetailsControllerApi extends BaseController<OrderDetailsRes> {
    private final OrderDetailsService orderDetailsService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        return super.getResponse(orderDetailsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable String id) {
        return super.getResponse(orderDetailsService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody OrderDetailsReq orderDetailsReq) {
        return super.getResponse(orderDetailsService.create(orderDetailsReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable String id, @RequestBody OrderDetailsReq orderDetailsReq) {
        return super.getResponse(orderDetailsService.update(id, orderDetailsReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        return super.getResponse(orderDetailsService.delete(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Response> getOrderDetailsByOrder(@PathVariable String orderId) {
        return super.getResponse(orderDetailsService.getOrderDetailsByOrderId(orderId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Response> getOrderDetailsByProduct(@PathVariable String productId) {
        return super.getResponse(orderDetailsService.getOrderDetailsByProductId(productId));
    }
}

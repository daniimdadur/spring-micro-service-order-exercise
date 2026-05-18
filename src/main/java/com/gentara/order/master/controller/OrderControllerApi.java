package com.gentara.order.master.controller;

import com.gentara.order.base.BaseController;
import com.gentara.order.base.Response;
import com.gentara.order.enums.OrderStatus;
import com.gentara.order.enums.PaymentStatus;
import com.gentara.order.master.model.request.OrderReq;
import com.gentara.order.master.model.request.PaymentCallbackReq;
import com.gentara.order.master.model.response.OrderRes;
import com.gentara.order.master.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
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
    public ResponseEntity<Response> create(
            @Parameter(
                    description = "Order Request",
                    required = true,
                    example = "i1a2b3c4d5e6f7g8h9i0j1k2l3m4n5o6"
            )
            @RequestHeader(value = "Idempotency-Key")
            String idempotencyKey,
            @RequestBody OrderReq orderReq
    ) {
        return super.getResponse(orderService.create(orderReq, idempotencyKey));
    }

    @PostMapping("/payment-callback")
    public ResponseEntity<Response> paymentCallback(@RequestBody PaymentCallbackReq request) {
        return super.getResponse(orderService.paymentCallback(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        return super.getResponse(orderService.delete(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Response> getOrdersByCustomer(@PathVariable String customerId) {
        return super.getResponse(orderService.getOrdersByCustomerId(customerId));
    }

    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<Response> getOrdersByStatus(@PathVariable OrderStatus orderStatus) {
        return super.getResponse(orderService.getOrdersByStatus(orderStatus));
    }

    @GetMapping("/payment-status/{paymentStatus}")
    public ResponseEntity<Response> getOrdersByPaymentStatus(@PathVariable PaymentStatus paymentStatus) {
        return super.getResponse(orderService.getOrdersByPaymentStatus(paymentStatus));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Response> cancelOrder(@PathVariable String id) {
        return super.getResponse(orderService.cancelOrder(id));
    }
}

package com.gentara.order.master.controller;

import com.gentara.order.base.BaseController;
import com.gentara.order.base.Response;
import com.gentara.order.master.model.response.PaymentRes;
import com.gentara.order.master.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/payment")
public class PaymentControllerApi extends BaseController<PaymentRes> {
    private final PaymentService paymentService;

    @PostMapping("/{orderId}")
    public ResponseEntity<Response> pay(@PathVariable String orderId) {
        return super.getResponse(paymentService.pay(orderId));
    }
}

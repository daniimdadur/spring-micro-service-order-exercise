package com.gentara.order.master.client;

import com.gentara.order.base.Response;
import com.gentara.order.master.model.request.PaymentReq;
import com.gentara.order.master.model.response.PaymentRes;
import lombok.NonNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "payment-service", url = "${client.payment-service.url}")
public interface PaymentClient {

    @PostMapping("/payment")
    ResponseEntity<@NonNull Response<PaymentRes>> pay(@RequestBody PaymentReq paymentReq);
}

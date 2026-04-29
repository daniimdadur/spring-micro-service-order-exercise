package com.gentara.order.master.client;

import com.gentara.order.master.model.request.OrderReq;
import com.gentara.order.master.model.response.OrderRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "payment-service", url = "${client.payment-service.url}")
public interface PaymentClient {

    @PostMapping("pay")
    Map<String, String> pay(@RequestBody OrderReq orderReq);
}

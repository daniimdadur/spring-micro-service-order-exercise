package com.gentara.order.master.controller;

import com.gentara.order.base.BaseController;
import com.gentara.order.base.Response;
import com.gentara.order.master.model.request.CustomerReq;
import com.gentara.order.master.model.response.CustomerRes;
import com.gentara.order.master.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/customer")
public class CustomerControllerApi extends BaseController<CustomerRes> {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        return super.getResponse(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable String id) {
        return super.getResponse(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody CustomerReq customerReq) {
        return super.getResponse(customerService.create(customerReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable String id, @RequestBody CustomerReq customerReq) {
        return super.getResponse(customerService.update(id, customerReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        return super.getResponse(customerService.delete(id));
    }
}

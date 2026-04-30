package com.gentara.order.master.controller;

import com.gentara.order.base.BaseController;
import com.gentara.order.base.Response;
import com.gentara.order.master.model.request.ProductReq;
import com.gentara.order.master.model.response.ProductRes;
import com.gentara.order.master.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/product")
public class ProductControllerApi extends BaseController<ProductRes> {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        return super.getResponse(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable String id) {
        return super.getResponse(productService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody ProductReq productReq) {
        return super.getResponse(productService.create(productReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable String id, @RequestBody ProductReq productReq) {
        return super.getResponse(productService.update(id, productReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        return super.getResponse(productService.delete(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Response> getProductsByCategory(@PathVariable String categoryId) {
        return super.getResponse(productService.getProductsByCategoryId(categoryId));
    }
}

package com.gentara.order.master.controller;

import com.gentara.order.base.BaseController;
import com.gentara.order.base.Response;
import com.gentara.order.master.model.request.CategoryReq;
import com.gentara.order.master.model.response.CategoryRes;
import com.gentara.order.master.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/orders/category")
public class CategoryControllerApi extends BaseController<CategoryRes> {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        return super.getResponse(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable String id) {
        return super.getResponse(categoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody CategoryReq categoryReq) {
        return super.getResponse(categoryService.create(categoryReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable String id, @RequestBody CategoryReq categoryReq) {
        return super.getResponse(categoryService.update(id, categoryReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        return super.getResponse(categoryService.delete(id));
    }
}

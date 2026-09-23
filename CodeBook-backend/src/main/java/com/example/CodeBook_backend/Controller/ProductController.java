package com.example.CodeBook_backend.Controller;

import com.example.CodeBook_backend.Service.ProductsService;
import com.example.CodeBook_backend.model.Products;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "https://codebook2.netlify.app")
public class ProductController {

    ProductsService service;

    ProductController(ProductsService service) {
        this.service = service;
    }

    @PostMapping
    public void addProduct(@RequestBody Products product) {
        service.add(product);
    }

    @GetMapping
    public List<Products> getProducts(
            @RequestParam(required = false, name = "name_like") String nameLike) {

        if (nameLike != null && !nameLike.isEmpty()) {
            return service.getSearchedProducts(nameLike);
        }

        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Products getProduct(@PathVariable("id") Integer id) {
        return service.getProduct(id);
    }
}

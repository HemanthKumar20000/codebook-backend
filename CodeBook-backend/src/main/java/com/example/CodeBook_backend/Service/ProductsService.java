package com.example.CodeBook_backend.Service;

import com.example.CodeBook_backend.model.Products;
import com.example.CodeBook_backend.repo.ProductsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    private final ProductsRepo repo;

    public ProductsService(ProductsRepo repo) {
        this.repo = repo;
    }

    public void add(Products product) {
        repo.save(product);
    }

    public List<Products> getProducts() {
        return repo.findAll();
    }

    public Products getProduct(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public List<Products> getSearchedProducts(String q) {
        return repo.findByNameContainingIgnoreCase(q);
    }
}


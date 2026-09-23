package com.example.CodeBook_backend.repo;

import com.example.CodeBook_backend.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Integer> {
    public List<Products> findByNameContainingIgnoreCase(String q);
}

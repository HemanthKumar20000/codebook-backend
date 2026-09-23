package com.example.CodeBook_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Products {
    @Id
    private Integer Id;
    private String name;
    private String overview;
    private String long_description;
    private Integer price;
    private String poster;
    private String image_local;
    private Integer rating;
    private boolean in_stock;
    private Integer size;
    private boolean best_seller;
}

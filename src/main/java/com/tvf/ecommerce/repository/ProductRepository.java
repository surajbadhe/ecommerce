package com.tvf.ecommerce.repository;

import com.tvf.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    public Product findProductByName(String name);
}

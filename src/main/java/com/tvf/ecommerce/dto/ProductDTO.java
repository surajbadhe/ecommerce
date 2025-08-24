package com.tvf.ecommerce.dto;

import com.tvf.ecommerce.model.Category;

public record ProductDTO(Integer id,
                         String name,
                         String description,
                         double price,
                         String imageUrl, Integer categoryId,String categoryName) {
}

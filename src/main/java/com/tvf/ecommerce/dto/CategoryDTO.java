package com.tvf.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public record CategoryDTO(Long id,
                          String categoryName,
                          String description,
                          String imageUrl) {
}

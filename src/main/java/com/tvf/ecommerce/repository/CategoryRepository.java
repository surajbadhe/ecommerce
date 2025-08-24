package com.tvf.ecommerce.repository;

import com.tvf.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryByCategoryName(String categoryName);
}

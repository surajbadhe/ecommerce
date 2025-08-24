package com.tvf.ecommerce.service;

import com.tvf.ecommerce.dto.CategoryDTO;
import com.tvf.ecommerce.mapper.CategoryMapper;
import com.tvf.ecommerce.model.Category;
import com.tvf.ecommerce.repository.CategoryRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    private final CategoryMapper mapper;


    public List<CategoryDTO> findAllCategory() {
        return mapper.toDTOList(categoryRepository.findAll());
    }

    public Category saveCategory(CategoryDTO categoryDTO) {

        return categoryRepository.save(mapper.toEntity(categoryDTO));
    }

    public CategoryDTO findCategoryByName(String categoryName) {
        return mapper.toDTO(categoryRepository.findCategoryByCategoryName(categoryName));
    }

    public Optional<Category> findCategoryById(@NotBlank Integer id) {
        return categoryRepository.findById(id);
    }


    public void deleteCategory(Integer categoryId) {
        Category category =categoryRepository.findById(categoryId).get();
        if(category.getId().equals(categoryId)){
            categoryRepository.delete(category);
        }
    }

    public void updateCategory(Integer categoryId,CategoryDTO newCategoryDTO) {
        Category category =categoryRepository.findById(categoryId).get();
        if(category.getId().equals(categoryId)){
            category.setCategoryName(newCategoryDTO.categoryName());
            category.setDescription(newCategoryDTO.description());
            category.setImageUrl(newCategoryDTO.imageUrl());
            categoryRepository.save(category);
        }
    }
}

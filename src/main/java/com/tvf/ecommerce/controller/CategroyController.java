package com.tvf.ecommerce.controller;

import com.tvf.ecommerce.dto.CategoryDTO;
import com.tvf.ecommerce.model.Category;
import com.tvf.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategroyController {
    CategoryService categoryService;

    @GetMapping("/{categoryName}")
    public ResponseEntity<CategoryDTO> findCategoryByCategoryName(@PathVariable @Valid String categoryName) {
        return new ResponseEntity<>(categoryService.findCategoryByName( categoryName ),HttpStatus.OK);
    }

    @GetMapping("/id/{categoryId}")
    public Optional<Category> findCategoryById(@PathVariable Integer categoryId) {
        return categoryService.findCategoryById(categoryId);
    }

    @GetMapping("/all")
    public List<CategoryDTO> findAllCategories() {
        return categoryService.findAllCategory();
    }
    @PostMapping("/")
    public ResponseEntity<Void> saveCategory(@RequestBody CategoryDTO categoryDTO) {

        if(Objects.nonNull(categoryService.findCategoryByName(categoryDTO.categoryName()))){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PatchMapping("/{categoryId}")
    public ResponseEntity<Void> updateCategory(@PathVariable Integer categoryId,@RequestBody CategoryDTO categoryDTO) {

        if(Objects.nonNull(categoryService.findCategoryById(categoryId))){
            categoryService.updateCategory(categoryId,categoryDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

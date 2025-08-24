package com.tvf.ecommerce.mapper;

import com.tvf.ecommerce.dto.CategoryDTO;
import com.tvf.ecommerce.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO dto);
    List<CategoryDTO> toDTOList(List<Category> categories); // List mapping
}
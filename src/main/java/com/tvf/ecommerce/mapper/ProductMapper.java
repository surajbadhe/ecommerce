package com.tvf.ecommerce.mapper;

import com.tvf.ecommerce.dto.ProductDTO;
import com.tvf.ecommerce.model.Category;
import com.tvf.ecommerce.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    // Entity → DTO
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.categoryName", target = "categoryName")
    ProductDTO toDTO(Product product);

    // DTO → Entity
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "mapCategoryIdToCategory")
    Product toEntity(ProductDTO dto);

    List<ProductDTO> toDTOList(List<Product> products);

    List<Product> toEntityList(List<ProductDTO> productDtos);

    // Custom mapping method
    @Named("mapCategoryIdToCategory")
    default Category mapCategoryIdToCategory(Integer categoryId) {
        if (categoryId == null) return null;
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }
}

package com.tvf.ecommerce.service;

import com.tvf.ecommerce.dto.ProductDTO;
import com.tvf.ecommerce.exception.ResourceNotFoundException;
import com.tvf.ecommerce.mapper.ProductMapper;
import com.tvf.ecommerce.model.Category;
import com.tvf.ecommerce.model.Product;
import com.tvf.ecommerce.repository.CategoryRepository;
import com.tvf.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductDTO createProduct(ProductDTO productDTO) {
        // Check category existence
        Category category = categoryRepository.findById(productDTO.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + productDTO.categoryId()));

        // Map DTO → Entity
        Product product = productMapper.toEntity(productDTO);
        product.setCategory(category);

        // Save entity
        Product savedProduct = productRepository.save(product);

        // Map back to DTO
        return productMapper.toDTO(savedProduct);
    }

    public List<ProductDTO> getAllProducts() {
        //return productMapper.toDTOList(productRepository.findAll());
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .toList();
    }

    public ProductDTO getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toDTO(product);
    }

    public ProductDTO updateProduct(Integer id, ProductDTO productDTO) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        Category category = categoryRepository.findById(productDTO.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id: " + productDTO.categoryId()));

        existing.setName(productDTO.name());
        existing.setDescription(productDTO.description());
        existing.setPrice(productDTO.price());
        existing.setImageUrl(productDTO.imageUrl());
        existing.setCategory(category);

        Product updated = productRepository.save(existing);
        return productMapper.toDTO(updated);
    }

    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}

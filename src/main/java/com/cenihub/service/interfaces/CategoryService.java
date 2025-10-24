package com.cenihub.service.interfaces;

import com.cenihub.dto.request.CategoryRequestDTO;
import com.cenihub.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO);
    CategoryResponseDTO getCategoryById(Long id);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO requestDTO);
    void deleteCategory(Long id);
    boolean existsByName(String name);
    boolean existsById(Long id);
}
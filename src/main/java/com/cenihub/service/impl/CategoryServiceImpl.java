package com.cenihub.service.impl;

import com.cenihub.dto.request.CategoryRequestDTO;
import com.cenihub.dto.response.CategoryResponseDTO;
import com.cenihub.exception.DuplicateResourceException;
import com.cenihub.exception.FailedToInsertToDb;
import com.cenihub.exception.RecordNotFound;
import com.cenihub.mapper.CategoryMapper;
import com.cenihub.model.Category;
import com.cenihub.repository.CategoryRepository;
import com.cenihub.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {
        if (existsByName(requestDTO.getName())) {
            throw new DuplicateResourceException("Category with name " + requestDTO.getName() + " already exists!");
        }
        try {
            Category category = categoryMapper.toEntity(requestDTO);
            Category savedCategory = categoryRepository.save(category);
            return categoryMapper.toResponseDTO(savedCategory);
        } catch (Exception ex) {
            throw new FailedToInsertToDb(ex.getCause());
        }
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound());
        return categoryMapper.toResponseDTO(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO requestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound());

        if (!category.getName().equals(requestDTO.getName()) && existsByName(requestDTO.getName())) {
            throw new DuplicateResourceException("Category with name " + requestDTO.getName() + " already exists!");
        }

        categoryMapper.updateEntityFromDTO(requestDTO, category);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFound());
        categoryRepository.delete(category);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }
}
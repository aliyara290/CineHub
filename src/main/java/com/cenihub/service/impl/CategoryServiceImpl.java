package com.cenihub.service.impl;

import com.cenihub.dto.request.CategoryRequestDTO;
import com.cenihub.dto.response.CategoryResponseDTO;
import com.cenihub.exception.DuplicateResourceException;
import com.cenihub.exception.FailedToDeleteException;
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
            throw new DuplicateResourceException("Category with name " + requestDTO.getName() + "already exist!");
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
        try {
            Category category = categoryRepository.findById(id).orElseThrow(RecordNotFound::new);
            return categoryMapper.toResponseDTO(category);
        } catch (Exception ex) {
            throw new RecordNotFound();
        }
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        try {
            return categoryRepository.findAll().stream().map(categoryMapper::toResponseDTO).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new RecordNotFound();
        }
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO requestDTO) {
        if(existsById(id)) {
            throw new RecordNotFound();
        }
        try {
            Category category = categoryMapper.toEntity(requestDTO);
            Category updatedCategory = categoryRepository.save(category);
            return categoryMapper.toResponseDTO(updatedCategory);
        } catch (Exception ex) {
            throw new FailedToInsertToDb(ex.getCause());
        }
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(RecordNotFound::new);
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
package org.mpravia.service;

import org.mpravia.dto.CategoryRequestDto;
import org.mpravia.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto);

    void deleteCategory(Long id);

    CategoryResponseDto getCategory(Long id);

    List<CategoryResponseDto> getCategories();
}

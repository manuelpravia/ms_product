package org.mpravia.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.mpravia.dto.CategoryRequestDto;
import org.mpravia.dto.CategoryResponseDto;
import org.mpravia.handler.AppException;
import org.mpravia.mapper.CategoryServiceMapper;
import org.mpravia.repository.CategoryRepository;
import org.mpravia.service.CategoryService;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    CategoryServiceMapper categoryServiceMapper;

    @Override
    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {

        categoryRepository.persist(categoryServiceMapper.toCategory(categoryRequestDto));
        var category = categoryRepository.findCategoryByName(categoryRequestDto.getName());

        return categoryServiceMapper.toCategoryResponseDto(category);
    }

    @Override
    @Transactional
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
        var category = categoryRepository.findById(id);

        if (category == null) {
            throw new AppException("EB01","La categoria no existe", Response.Status.NOT_FOUND);
        }

        categoryServiceMapper.updateCategory(categoryRequestDto, category);
        categoryRepository.persist(category);

        var categoryUpdate = categoryRepository.findById(id);

        return categoryServiceMapper.toCategoryResponseDto(categoryUpdate);
    }

    @Override
    public void deleteCategory(Long id) {
        var category = categoryRepository.findById(id);

        if (category == null) {
            throw new AppException("EB01","La categoria no existe", Response.Status.NOT_FOUND);
        }

        categoryRepository.delete(category);

    }

    @Override
    public CategoryResponseDto getCategory(Long id) {

        var category = categoryRepository.findById(id);
        if (category == null) {
            throw new AppException("EB01","La categoria no existe", Response.Status.NOT_FOUND);
        }
        return categoryServiceMapper.toCategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> getCategories() {

        return categoryRepository.findAll()
                .list()
                .stream()
                .map(categoryServiceMapper::toCategoryResponseDto)
                .toList();
    }


}

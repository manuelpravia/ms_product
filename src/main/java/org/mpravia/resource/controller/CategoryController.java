package org.mpravia.resource.controller;

import jakarta.inject.Inject;
import org.mpravia.api.CategoryApi;
import org.mpravia.mapper.CategoryControllerMapper;
import org.mpravia.model.CategoryRequest;
import org.mpravia.model.CategoryResponse;
import org.mpravia.service.CategoryService;

import java.util.List;

public class CategoryController implements CategoryApi {

    @Inject
    CategoryService categoryService;

    @Inject
    CategoryControllerMapper categoryControllerMapper;


    /**
     *
     *
     * @param categoryRequest
     * @return Category created
     */
    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {

        var categoryDto = categoryControllerMapper.toCategoryRequestDto(categoryRequest);
        return categoryControllerMapper.toCategoryResponse(categoryService.createCategory(categoryDto));
    }

    /**
     *
     *
     * @param id
     * @return Successful operation
     */
    @Override
    public CategoryResponse getCategoryById(Long id) {

        return categoryControllerMapper.toCategoryResponse(categoryService.getCategory(id));
    }

    /**
     *
     *
     * @return List of categories
     */
    @Override
    public List<CategoryResponse> listCategories() {

        return categoryService.getCategories()
                .stream()
                .map(categoryControllerMapper::toCategoryResponse)
                .toList();
    }

    /**
     *
     *
     * @param id
     * @param categoryRequest
     * @return Category updated
     */
    @Override
    public void updateCategory(Long id, CategoryRequest categoryRequest) {

        categoryService.updateCategory(id, categoryControllerMapper.toCategoryRequestDto(categoryRequest));

    }
}

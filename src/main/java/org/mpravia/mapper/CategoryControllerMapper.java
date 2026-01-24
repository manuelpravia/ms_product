package org.mpravia.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mpravia.dto.CategoryRequestDto;
import org.mpravia.dto.CategoryResponseDto;
import org.mpravia.model.CategoryRequest;
import org.mpravia.model.CategoryResponse;

@ApplicationScoped
@Mapper(componentModel = "jakarta")
public interface CategoryControllerMapper {

    CategoryRequestDto toCategoryRequestDto(CategoryRequest categoryRequest);

    CategoryResponse toCategoryResponse(CategoryResponseDto categoryResponseDto);
}

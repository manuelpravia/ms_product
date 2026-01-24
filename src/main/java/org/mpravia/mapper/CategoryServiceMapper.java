package org.mpravia.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mpravia.dto.CategoryRequestDto;
import org.mpravia.dto.CategoryResponseDto;
import org.mpravia.repository.entity.Category;

@ApplicationScoped
@Mapper(componentModel = "jakarta")
public interface CategoryServiceMapper {

    Category toCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto toCategoryResponseDto(Category category);

    @Mapping(target = "id", ignore = true)
    void updateCategory(CategoryRequestDto dto, @MappingTarget Category category);
}

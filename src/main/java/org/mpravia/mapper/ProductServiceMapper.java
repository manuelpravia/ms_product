package org.mpravia.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mpravia.dto.ProductRequestDto;
import org.mpravia.dto.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mpravia.repository.entity.Product;

@ApplicationScoped
@Mapper(componentModel = "jakarta")
public interface ProductServiceMapper {

    ProductResponseDto toProductResponseDto(Product product);

    Product toProduct(ProductRequestDto productRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateProduct(ProductRequestDto dto, @MappingTarget Product product);
}

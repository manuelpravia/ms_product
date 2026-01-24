package org.mpravia.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mpravia.dto.ProductPageResponseDto;
import org.mpravia.dto.ProductRequestDto;
import org.mpravia.dto.ProductResponseDto;
import org.mpravia.model.ProductPageResponse;
import org.mpravia.model.ProductRequest;
import org.mpravia.model.ProductResponse;

import java.util.List;

@ApplicationScoped
@Mapper(componentModel = "jakarta")
public interface ProductControllerMapper {

    ProductRequestDto toProductRequestDto(ProductRequest productRequest);

    ProductResponse toProductResponse(ProductResponseDto productResponseDto);

    @Mapping(target = "content", source = "productResponseDto")
    ProductPageResponse toProductPageResponse(ProductPageResponseDto productPageResponseDto);

}

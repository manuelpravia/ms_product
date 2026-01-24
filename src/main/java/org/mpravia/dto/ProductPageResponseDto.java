package org.mpravia.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductPageResponseDto {
    private Integer totalCount;
    private Integer totalPages;
    private Integer pageIndex;
    private Integer pageSize;
    private List<ProductResponseDto> productResponseDto;

}

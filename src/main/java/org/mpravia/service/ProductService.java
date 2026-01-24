package org.mpravia.service;

import org.mpravia.dto.ProductPageResponseDto;
import org.mpravia.dto.ProductRequestDto;
import org.mpravia.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProductById(long productId);

    ProductPageResponseDto getProducts(Integer page, Integer size);

    ProductResponseDto persistProduct(ProductRequestDto productRequestDto);

    ProductResponseDto updateProduct(long productId, ProductRequestDto productRequestDto);

    void deleteProduct(long idProduct);

}

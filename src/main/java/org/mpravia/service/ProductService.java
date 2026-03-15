package org.mpravia.service;

import org.mpravia.dto.ProductPageResponseDto;
import org.mpravia.dto.ProductRequestDto;
import org.mpravia.dto.ProductResponseDto;
import org.mpravia.message.consumer.dto.ProductSold;

import java.util.List;

public interface ProductService {

    ProductResponseDto getProductById(long productId);

    ProductPageResponseDto getProducts(Integer page, Integer size);

    ProductResponseDto persistProduct(ProductRequestDto productRequestDto);

    ProductResponseDto updateProduct(long productId, ProductRequestDto productRequestDto);

    void deleteProduct(long idProduct);

    List<ProductResponseDto> getProductsByCodes(List<String> codes);

    void updateStockEvent(List<ProductSold> productSold);
}

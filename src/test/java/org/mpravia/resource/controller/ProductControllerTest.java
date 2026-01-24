package org.mpravia.resource.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mpravia.dto.ProductPageResponseDto;
import org.mpravia.dto.ProductRequestDto;
import org.mpravia.dto.ProductResponseDto;
import org.mpravia.mapper.ProductControllerMapperImpl;
import org.mpravia.mocks.ProductMock;
import org.mpravia.model.ProductPageResponse;
import org.mpravia.model.ProductRequest;
import org.mpravia.model.ProductResponse;
import org.mpravia.service.impl.ProductServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductServiceImpl productService;

    @InjectMocks
    ProductController productController;

    @Spy
    ProductControllerMapperImpl productControllerMapper;

    @Test
    void addProduct() {
        ProductRequest productRequest = ProductMock.getProductRequest();
        ProductResponseDto productResponseDto = ProductMock.getProductResponseDto();

        Mockito.when(productService.persistProduct(any(ProductRequestDto.class)))
                .thenReturn(productResponseDto);

        productController.addProduct(productRequest);

        assertEquals(productRequest.getName(), productResponseDto.getName());

    }

    @Test
    void deleteProduct() {
        long id = 1L;

        productController.deleteProduct(id);
    }

    @Test
    void getProductById() {
        long id = 1L;
        ProductResponseDto productResponseDto = ProductMock.getProductResponseDto();
        Mockito.when(productService.getProductById(anyLong())).thenReturn(productResponseDto);

        ProductResponse productResponse = productController.getProductById(id);

        assertEquals(productResponseDto.getCode(), productResponse.getCode());
    }

    @Test
    void listProducts() {

        ProductPageResponseDto productPageResponseDto = ProductMock.getProductPageResponseDto();
        Mockito.when(productService.getProducts(anyInt(), anyInt()))
                .thenReturn(productPageResponseDto);

        ProductPageResponse productPageResponse = productController.listProducts(anyInt(), anyInt());

        assert(productPageResponseDto != null);
        assert(productPageResponseDto.getProductResponseDto().size() == productPageResponse.getContent().size());
    }

    @Test
    void updateProduct() {
        ProductRequest  productRequest = ProductMock.getProductRequest();

        productController.updateProduct(anyLong(), productRequest);

    }
}
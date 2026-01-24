package org.mpravia.mocks;

import org.mpravia.dto.ProductPageResponseDto;
import org.mpravia.dto.ProductRequestDto;
import org.mpravia.dto.ProductResponseDto;
import org.mpravia.model.ProductRequest;
import org.mpravia.model.ProductResponse;
import org.mpravia.repository.entity.Product;
import org.mpravia.utils.TestUtils;

public class ProductMock {

    public static Product getProduct() {
        return TestUtils.readJson( "entity/Product.json", Product.class);
    }

    public static ProductResponseDto getProductResponseDto() {
        return TestUtils.readJson( "dto/ProductResponseDto.json", ProductResponseDto.class);
    }

    public static ProductRequestDto getProductRequestDto() {
        return TestUtils.readJson( "dto/ProductRequestDto.json", ProductRequestDto.class);
    }

    public static ProductPageResponseDto getProductPageResponseDto() {
        return TestUtils.readJson( "dto/ProductPageResponseDto.json", ProductPageResponseDto.class);
    }

    public static ProductResponse getProductResponse() {
        return TestUtils.readJson( "generados/ProductResponse.json", ProductResponse.class);
    }

    public static ProductRequest getProductRequest() {
        return TestUtils.readJson( "generados/ProductRequest.json", ProductRequest.class);
    }

}

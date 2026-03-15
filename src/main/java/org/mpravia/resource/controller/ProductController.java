package org.mpravia.resource.controller;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import org.mpravia.api.ProductApi;
import org.mpravia.mapper.ProductControllerMapper;
import org.mpravia.model.ProductCodesRequest;
import org.mpravia.model.ProductPageResponse;
import org.mpravia.model.ProductRequest;
import org.mpravia.model.ProductResponse;
import org.mpravia.service.ProductService;

import java.util.List;


public class ProductController implements ProductApi {

    @Inject
    ProductService productService;

    @Inject
    ProductControllerMapper productControllerMapper;
    /**
     * Add a new product to the database
     *
     * @param productRequest structure product creation
     * @return Successful operation
     * @return Invalid input
     */
    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Log.info("Adding product " + productRequest.getName());
        var productDto = productControllerMapper.toProductRequestDto(productRequest);
        return productControllerMapper.toProductResponse(productService.persistProduct(productDto));
    }

    /**
     *
     *
     * @param id
     * @return Product deleted
     */
    @Override
    public void deleteProduct(Long id) {
        Log.info("Deleting product " + id);
        productService.deleteProduct(id);

    }

    /**
     *
     *
     * @param id
     * @return Successful operation
     */
    @Override
    public ProductResponse getProductById(Long id) {
        Log.info("Getting product " + id);

        return productControllerMapper.toProductResponse(productService.getProductById(id));
    }

    /**
     *
     *
     * @param productCodesRequest
     * @return Successful operation
     * @return Invalid input
     */
    @Override
    public List<ProductResponse> getProductsByCode(ProductCodesRequest productCodesRequest) {
        return productService.getProductsByCodes(productCodesRequest.getCodes())
                .stream()
                .map(productControllerMapper::toProductResponse)
                .toList();
    }

    /**
     *
     *
     * @param page Page number (starting from 0)
     * @param size Number of records per page
     * @return Successful operation
     * @return No products found
     */
    @Override
    public ProductPageResponse listProducts(Integer page, Integer size) {

        var pageProduct = productService.getProducts(page, size);
        return productControllerMapper.toProductPageResponse(pageProduct);
    }


    /**
     *
     *
     * @param id
     * @param productRequest
     * @return Successful operation
     */
    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {

        var productDto = productControllerMapper.toProductRequestDto(productRequest);
        Log.info("Controller: Updating product" + productRequest.getName());
        productService.updateProduct(id, productDto);
    }


}

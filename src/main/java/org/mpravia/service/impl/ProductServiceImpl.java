package org.mpravia.service.impl;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.mpravia.dto.ProductPageResponseDto;
import org.mpravia.dto.ProductRequestDto;
import org.mpravia.dto.ProductResponseDto;
import org.mpravia.handler.AppException;
import org.mpravia.mapper.ProductServiceMapper;
import org.mpravia.repository.ProductRepository;
import org.mpravia.repository.entity.Product;
import org.mpravia.service.ProductService;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    @Inject
    ProductRepository  productRepository;

    @Inject
    ProductServiceMapper productServiceMapper;

    @Override
    public ProductResponseDto getProductById(long productId) {

        var product = productRepository.findById(productId);
        if (product == null) {
            throw new AppException("EB01","El producto no existe", Response.Status.NOT_FOUND);
        }
        return productServiceMapper.toProductResponseDto(product);
    }

    @Override
    public ProductPageResponseDto getProducts(Integer page, Integer size) {

        PanacheQuery<Product> query = productRepository.findAll()
                .page(Page.of(page, size));

        List<ProductResponseDto> productResponseDto = query.list()
                .stream()
                .map(productServiceMapper::toProductResponseDto)
                .toList();

        ProductPageResponseDto productPaginationResponseDto = new ProductPageResponseDto();
        productPaginationResponseDto.setTotalCount((int) query.count());
        productPaginationResponseDto.setTotalPages(query.pageCount());
        productPaginationResponseDto.setPageIndex(page);
        productPaginationResponseDto.setPageSize(size);
        productPaginationResponseDto.setProductResponseDto(productResponseDto);

        return productPaginationResponseDto;
    }

    @Override
    @Transactional
    public ProductResponseDto persistProduct(ProductRequestDto productRequestDto) {

        var product = productServiceMapper.toProduct(productRequestDto);
        product.setCode(generateUniqueRandomCode());
        productRepository.persist(product);
        return productServiceMapper.toProductResponseDto(product);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(long productId, ProductRequestDto productRequestDto) {



        Log.info("Service: Update product" + productRequestDto.getName());
        var product = productRepository.findById(productId);
        if (product == null) {
            throw new AppException("EB01","El producto no existe", Response.Status.NOT_FOUND);
        }
        productServiceMapper.updateProduct(productRequestDto, product);

        productRepository.persist(product);

        return productServiceMapper.toProductResponseDto(product);

    }

    @Override
    @Transactional
    public void deleteProduct(long idProduct) {

        var product = productRepository.findById(idProduct);

        if (product == null) {
            throw new AppException("EB01","El producto no existe", Response.Status.NOT_FOUND);
        }
        productRepository.deleteById(idProduct);
    }

    private String generateUniqueRandomCode() {
        String newCode;
        boolean existCode;
        do {
            newCode = UUID.randomUUID().toString().substring(0,8).toUpperCase();
            existCode = productRepository.find("code", newCode).firstResultOptional().isPresent();
        }while (existCode);

        return newCode;
    }

}

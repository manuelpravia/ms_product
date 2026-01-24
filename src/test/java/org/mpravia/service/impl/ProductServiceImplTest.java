package org.mpravia.service.impl;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mpravia.dto.ProductPageResponseDto;
import org.mpravia.dto.ProductRequestDto;
import org.mpravia.dto.ProductResponseDto;
import org.mpravia.handler.AppException;
import org.mpravia.mapper.ProductServiceMapperImpl;
import org.mpravia.mocks.ProductMock;
import org.mpravia.repository.ProductRepository;
import org.mpravia.repository.entity.Product;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Spy
    ProductServiceMapperImpl productServiceMapper;


    @Test
    @DisplayName("Return product by id")
    void getProductById() {
        Product product = ProductMock.getProduct();

        Mockito.when(productRepository.findById(product.getId())).thenReturn(product);

        ProductResponseDto productResponseDto = productServiceImpl.getProductById(product.getId());

        assert(productResponseDto.getCode().equals(product.getCode()));
    }

    @Test
    @DisplayName("Debe lanzar AppException cuando el ID del producto no existe")
    void getProductById_WhenProductDoesNotExist_ShouldThrowAppException() {
        long productId = 99L;
        when(productRepository.findById(productId)).thenReturn(null);
        AppException exception = assertThrows(AppException.class, () -> {
            productServiceImpl.getProductById(productId);
        });
        // Verificamos que los datos dentro de la excepción sean los correctos
        assertEquals("EB01", exception.getErrorCode(), "El código de error debe ser EB01");
        assertEquals("El producto no existe", exception.getMessage());
        assertEquals(Response.Status.NOT_FOUND, exception.getStatus(), "El estatus debe ser 404 NOT_FOUND");

        // Verificamos que nunca se llamó al mapper, ya que la excepción cortó el flujo
        verifyNoInteractions(productServiceMapper);

        // Verificamos que se consultó al repositorio exactamente una vez
        verify(productRepository, times(1)).findById(productId);

    }

    @Test
    @DisplayName("Return pagination product")
    void getProductsPaginated() {
        int page = 0;
        int size = 10;
        Product product = ProductMock.getProduct();

        PanacheQuery<Product> queryMock = mock(PanacheQuery.class);

        // Mockeamos la cadena: repository.findAll().page(...)
        when(productRepository.findAll()).thenReturn(queryMock);
        when(queryMock.page(any(Page.class))).thenReturn(queryMock);

        // Mockeamos los resultados de la query
        when(queryMock.list()).thenReturn(List.of(product));
        when(queryMock.count()).thenReturn(50L);
        when(queryMock.pageCount()).thenReturn(5);

        ProductPageResponseDto result = productServiceImpl.getProducts(page, size);

        assert(50 == result.getTotalCount());
        assert(5 ==result.getTotalPages());
        assert(page == result.getPageIndex());
        assert(size == result.getPageSize());
        assert(1 == result.getProductResponseDto().size());
        assert("Agua Cielo".equals(result.getProductResponseDto().getFirst().getName()));

        // Verificar que se llamaron a los métodos correctos
        verify(productRepository).findAll();
        verify(queryMock).page(argThat(p -> p.index == page && p.size == size));
    }

    @Test
    @DisplayName("Return pagination empty when not found data")
    void returnPaginationEmptyWhenNotFoundData() {
        PanacheQuery<Product> queryMock = mock(PanacheQuery.class);
        when(productRepository.findAll()).thenReturn(queryMock);
        when(queryMock.page(any())).thenReturn(queryMock);
        when(queryMock.list()).thenReturn(List.of()); // Lista vacía
        when(queryMock.count()).thenReturn(0L);

        ProductPageResponseDto result = productServiceImpl.getProducts(0, 10);

        assert(result.getProductResponseDto().isEmpty());
        assert(0 == result.getTotalCount());
    }

    @Test
    void persistProduct() {
        ProductRequestDto productRequestDto = ProductMock.getProductRequestDto();
        PanacheQuery query = mock(PanacheQuery.class);
        when(productRepository.find(eq("code"), anyString())).thenReturn(query);
        when(query.firstResultOptional()).thenReturn(Optional.empty());

        ProductResponseDto productResponseDto = productServiceImpl.persistProduct(productRequestDto);

        assert(productResponseDto.getCode() != null);
    }

    @Test
    void updateProduct() {
        Product product = ProductMock.getProduct();
        ProductRequestDto productRequestDto = ProductMock.getProductRequestDto();
        Mockito.when(productRepository.findById(product.getId())).thenReturn(product);

        ProductResponseDto productResponseDto = productServiceImpl.updateProduct(2L, productRequestDto);

        assert(productRequestDto.getPrice() == productResponseDto.getPrice());

    }

    @Test
    void deleteProduct() {
        Product product = ProductMock.getProduct();
        Mockito.when(productRepository.findById(product.getId())).thenReturn(product);

        productServiceImpl.deleteProduct(product.getId());

    }
}
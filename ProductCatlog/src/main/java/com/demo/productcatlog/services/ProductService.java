package com.demo.productcatlog.services;

import com.demo.productcatlog.dto.GenericProductDto;

import java.util.List;

public interface ProductService {

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id);

    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProduct(Long id);
}

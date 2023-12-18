package com.demo.productcatlog.controllers;

import com.demo.productcatlog.dto.GenericProductDto;
import com.demo.productcatlog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/")
public class ProductController {


    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductServiceImpl") ProductService productService){
        this.productService=productService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
      return productService.getAllProducts();
    }
    @GetMapping("{id}")
    public GenericProductDto getProductsById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id){
       return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.NOT_FOUND
        );

    }
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);

    }
    @PutMapping("{id}")
    public void updateProductById(){

    }
}

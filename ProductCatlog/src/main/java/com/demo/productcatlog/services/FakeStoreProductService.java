package com.demo.productcatlog.services;

import com.demo.productcatlog.dto.FakeStoreProductDto;
import com.demo.productcatlog.dto.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductServiceImpl")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String getAllProductsRequestUrl = "https://fakestoreapi.com/products/";
    private String createProductRequestUrl = "https://fakestoreapi.com/products/";
    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";


    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        return product;
    }
    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
      ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
              createProductRequestUrl,product, GenericProductDto.class
      );
      return response.getBody();
    }

    public List<GenericProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(
                getAllProductsRequestUrl, FakeStoreProductDto[].class);

        List<GenericProductDto> answer = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(response.getBody()).toList()){
//            GenericProductDto product = new GenericProductDto();
//            product.setImage(fakeStoreProductDto.getImage());
//            product.setDescription(fakeStoreProductDto.getDescription());
//            product.setCategory(fakeStoreProductDto.getCategory());
//            product.setTitle(fakeStoreProductDto.getTitle());
//            product.setPrice(fakeStoreProductDto.getPrice());


            answer.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }
        return answer;
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        // FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class,id,"name");
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

//        GenericProductDto product = new GenericProductDto();
//        product.setImage(fakeStoreProductDto.getImage());
//        product.setDescription(fakeStoreProductDto.getDescription());
//        product.setCategory(fakeStoreProductDto.getCategory());
//        product.setTitle(fakeStoreProductDto.getTitle());
//        product.setPrice(fakeStoreProductDto.getPrice());

        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);

       // return product;
        //return null;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(
                specificProductRequestUrl, HttpMethod.DELETE,requestCallback,responseExtractor,id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

//        GenericProductDto product = new GenericProductDto();
         // product.setId(fakeStoreProductDto.getId());
//        product.setImage(fakeStoreProductDto.getImage());
//        product.setDescription(fakeStoreProductDto.getDescription());
//        product.setCategory(fakeStoreProductDto.getCategory());
//        product.setTitle(fakeStoreProductDto.getTitle());
//        product.setPrice(fakeStoreProductDto.getPrice());

        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }
}

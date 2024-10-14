package com.example.productservice.controllers;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.InvalidParameterException;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.PriceRepository;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")

public class ProductController {
    //Constructor Injection
    private ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductService (@Qualifier("selfProductServiceImpl") ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/{id}")
//    public GenericProductDto getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
//                                            @PathVariable("id") String id) throws ProductNotFoundException,
//            InvalidParameterException {
//        return productService.getProductById(authToken,id);
//    }
        public GenericProductDto getProductById(@PathVariable("id") String id) throws ProductNotFoundException,
            InvalidParameterException {
        return productService.getProductById(id);
    }

    @GetMapping("")
    public List<GenericProductDto> getAllProducts(){
        System.out.println("INVOKED");
        System.out.println("All products invoked");
        //return null;
        return productService.getAllProducts();
    }
    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") String id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }
    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }
    @PatchMapping()
    public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto) throws ProductNotFoundException {
        return productService.updateProductById(genericProductDto);
    }

}

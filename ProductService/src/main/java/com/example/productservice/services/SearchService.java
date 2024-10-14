package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.models.Product;
import com.example.productservice.models.ProductEs;
import com.example.productservice.models.SortParam;
import com.example.productservice.repositories.ProductRepository;

import com.example.productservice.repositories.ProductRepositoryES;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final ProductRepository productRepository;
    private final ProductRepositoryES productRepositoryES;

    public SearchService(ProductRepository productRepository,
                         ProductRepositoryES productRepositoryES) {
        this.productRepository = productRepository;
        this.productRepositoryES = productRepositoryES;
    }

    public Page<GenericProductDto> searchProduct(String query, int pageNumber, int pageSize, List<SortParam> sortParams){
        System.out.println("PRINT");
        Sort sort= null;
        System.out.println(sortParams.get(0).getSortParamName());
        if(sortParams.get(0).getSortType().equals("ASC")){
            sort=Sort.by(sortParams.get(0).getSortParamName()).ascending();
        }else{
            sort=Sort.by(sortParams.get(0).getSortParamName()).descending();
        }
        for(int i=1;i<sortParams.size();i++){
            if(sortParams.get(i).getSortType().equals("ASC")){
                sort.and(Sort.by(sortParams.get(i).getSortParamName())).ascending();
            }else{
                sort.and(Sort.by(sortParams.get(i).getSortParamName())).descending();
            }
        }

        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        List<Product> products=productRepository.findAllByTitleContainingIgnoreCase(query,pageable);
        List<GenericProductDto> genericProductDtos=new ArrayList<>();

        for(Product product :products){
            GenericProductDto genericProductDto = product.toGenericProductDtos(product);
            genericProductDtos.add(genericProductDto);
        }
        Page<GenericProductDto> genericProductDtoPage=new PageImpl<>(
                genericProductDtos,pageable,5);

        return genericProductDtoPage;
    }
    public Page<GenericProductDto> searchProductUsingES(String query, int pageNumber, int pageSize, List<SortParam> sortParams){
        Sort sort= null;
        if(sortParams.get(0).getSortType().equals("ASC")){
            sort=Sort.by(sortParams.get(0).getSortParamName()).ascending();
        }else{
            sort=Sort.by(sortParams.get(0).getSortParamName()).descending();
        }
        for(int i=1;i<sortParams.size();i++){
            if(sortParams.get(i).getSortType().equals("ASC")){
                sort.and(Sort.by(sortParams.get(i).getSortParamName())).ascending();
            }else{
                sort.and(Sort.by(sortParams.get(i).getSortParamName())).descending();
            }
        }
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        List<ProductEs> productEs=productRepositoryES.findAllByTitle(query,pageable);
        List<GenericProductDto> genericProductDtos=new ArrayList<>();
        for(ProductEs product : productEs){
            GenericProductDto genericProductDto = product.toGenericProductDto(product);
            System.out.println(genericProductDto.getPrice());
            genericProductDtos.add(genericProductDto);
        }
        Page<GenericProductDto> genericProductDtoPage=new PageImpl<>(
                genericProductDtos,pageable,5);
        return genericProductDtoPage;
    }
}

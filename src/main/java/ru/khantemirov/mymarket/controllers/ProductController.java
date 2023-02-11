package ru.khantemirov.mymarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.mymarket.converters.ProductConverter;
import ru.khantemirov.mymarket.dtos.ProductDto;
import ru.khantemirov.mymarket.entities.Product;
import ru.khantemirov.mymarket.exceptions.AppError;
import ru.khantemirov.mymarket.exceptions.ResourceNotFoundException;
import ru.khantemirov.mymarket.services.CategoryService;
import ru.khantemirov.mymarket.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> findAllProducts(){
        return productService.findAll().stream()
                .map(productConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable long id){
        Product product = productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Продукт не найден, id:"+id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto){
        Product product = productService.createNewProduct(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable long id){
        productService.deleteById(id);
    }

}

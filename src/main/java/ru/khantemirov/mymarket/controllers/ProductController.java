package ru.khantemirov.mymarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.mymarket.dtos.ProductDto;
import ru.khantemirov.mymarket.entities.Product;
import ru.khantemirov.mymarket.exceptions.AppError;
import ru.khantemirov.mymarket.exceptions.ResourceNotFoundException;
import ru.khantemirov.mymarket.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts(){
        return productService.findAll().stream()
                .map(product -> new ProductDto(product.getId(), product.getTitle(),product.getPrice()))
                .collect(Collectors.toList());
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> findProductById(@PathVariable long id){
//        Optional<Product> product = productService.findById(id);
//        if (!product.isPresent()){
//            ResponseEntity<AppError> err = new ResponseEntity<AppError>(new AppError(HttpStatus.NOT_FOUND.value(),
//                    "Продукт не найден, id:"+id), HttpStatus.NOT_FOUND);
//            return err;
//        }
//        return new ResponseEntity<>(product.get(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable long id){
        Product product = productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Продукт не найден, id:"+id));
        return new ProductDto(product.getId(), product.getTitle(),product.getPrice());
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable long id){
        productService.deleteById(id);
    }

}

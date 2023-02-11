package ru.khantemirov.mymarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.mymarket.converters.CategoryConverter;
import ru.khantemirov.mymarket.converters.ProductConverter;
import ru.khantemirov.mymarket.dtos.CategoryDto;
import ru.khantemirov.mymarket.dtos.ProductDto;
import ru.khantemirov.mymarket.entities.Product;
import ru.khantemirov.mymarket.exceptions.ResourceNotFoundException;
import ru.khantemirov.mymarket.services.CategoryService;
import ru.khantemirov.mymarket.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;

    @GetMapping
    public List<CategoryDto> findAllCategories() {
        return categoryService.findAll().stream()
                .map(categoryConverter::entityToDto)
                .collect(Collectors.toList());
    }
}

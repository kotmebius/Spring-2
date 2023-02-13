package ru.khantemirov.mymarket.core.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.mymarket.api.CategoryDto;
import ru.khantemirov.mymarket.core.converters.CategoryConverter;
import ru.khantemirov.mymarket.core.services.CategoryService;

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

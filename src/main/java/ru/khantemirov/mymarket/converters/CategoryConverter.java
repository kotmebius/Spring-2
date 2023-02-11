package ru.khantemirov.mymarket.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.khantemirov.mymarket.dtos.CategoryDto;
import ru.khantemirov.mymarket.dtos.ProductDto;
import ru.khantemirov.mymarket.entities.Category;
import ru.khantemirov.mymarket.entities.Product;
import ru.khantemirov.mymarket.exceptions.ResourceNotFoundException;
import ru.khantemirov.mymarket.services.CategoryService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final CategoryService categoryService;
    private final ProductConverter productConverter;

    public CategoryDto entityToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setProducts(category.getProducts().stream()
                .map(productConverter::entityToDto)
                .collect(Collectors.toList()));

        return categoryDto;
    }


}

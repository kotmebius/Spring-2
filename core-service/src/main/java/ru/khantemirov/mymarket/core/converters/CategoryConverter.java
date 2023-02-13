package ru.khantemirov.mymarket.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.khantemirov.mymarket.api.CategoryDto;
import ru.khantemirov.mymarket.core.entities.Category;
import ru.khantemirov.mymarket.core.services.CategoryService;

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

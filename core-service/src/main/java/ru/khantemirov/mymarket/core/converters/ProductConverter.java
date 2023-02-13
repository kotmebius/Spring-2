package ru.khantemirov.mymarket.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.khantemirov.mymarket.api.ProductDto;
import ru.khantemirov.mymarket.api.ResourceNotFoundException;
import ru.khantemirov.mymarket.core.entities.Category;
import ru.khantemirov.mymarket.core.entities.Product;
import ru.khantemirov.mymarket.core.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getCategory().getTitle(),product.getPrice());
    }

    public Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getTitle()).orElseThrow(() ->
                new ResourceNotFoundException("Не найдена категория конвертируемого продукта"));
        product.setCategory(category);
        return product;
    }
}

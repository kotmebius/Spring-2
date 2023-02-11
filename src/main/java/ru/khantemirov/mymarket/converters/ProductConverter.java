package ru.khantemirov.mymarket.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.khantemirov.mymarket.dtos.ProductDto;
import ru.khantemirov.mymarket.entities.Category;
import ru.khantemirov.mymarket.entities.Product;
import ru.khantemirov.mymarket.exceptions.ResourceNotFoundException;
import ru.khantemirov.mymarket.services.CategoryService;

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

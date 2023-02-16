package ru.khantemirov.mymarket.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.khantemirov.mymarket.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integration.coreService}")
    private String coreService;


    public Optional<ProductDto> getProductById(Long id) {
        return Optional.ofNullable(restTemplate.getForObject(coreService+"api/v1/products/" + id,
                ProductDto.class));
    }
}

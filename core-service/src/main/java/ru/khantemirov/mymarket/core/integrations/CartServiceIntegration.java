package ru.khantemirov.mymarket.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.khantemirov.mymarket.api.CartDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;
    @Value("${integration.cartService}")
    private String cartService;

    public Optional<CartDto> getCurrentCart() {
        return Optional.ofNullable(restTemplate.getForObject(cartService+"api/v1/cart/",
                CartDto.class));
    }

    public void clearCart() {
        restTemplate.delete(cartService+"api/v1/cart/clear");
    }

}

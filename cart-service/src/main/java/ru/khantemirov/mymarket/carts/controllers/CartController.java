package ru.khantemirov.mymarket.carts.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.mymarket.api.CartDto;
import ru.khantemirov.mymarket.carts.converters.CartConverter;
import ru.khantemirov.mymarket.carts.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable long id) {
        cartService.addProduct(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable long id) {
        cartService.deleteById(id);
    }

    @PostMapping("/inc/{id}")
    public void incCartItem(@PathVariable long id) {
        cartService.incCartItem(id);
    }

    @PostMapping("/dec/{id}")
    public void decCartItem(@PathVariable long id) {
        cartService.decCartItem(id);
    }

    @PostMapping("/flush")
    public void clearCart() {
        cartService.clearCart();
    }

}

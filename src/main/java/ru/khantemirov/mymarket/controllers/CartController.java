package ru.khantemirov.mymarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.mymarket.model.Cart;
import ru.khantemirov.mymarket.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
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

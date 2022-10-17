package ru.khantemirov.mymarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.mymarket.dtos.Cart;
import ru.khantemirov.mymarket.entities.Product;
import ru.khantemirov.mymarket.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCurrentCart(){
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable long id){
        cartService.addProduct(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable long id){
        cartService.deleteById(id);
    }

    @PostMapping("/inc/{id}")
    public void incCartItem(@PathVariable long id){
        cartService.incCartItem(id);
    }
    @PostMapping("/dec/{id}")
    public void decCartItem(@PathVariable long id){
        cartService.decCartItem(id);
    }
    @PostMapping("/flush")
    public void flushCart(){
        cartService.flushCart();
    }

}

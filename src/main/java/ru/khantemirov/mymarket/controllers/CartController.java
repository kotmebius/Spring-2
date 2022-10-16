package ru.khantemirov.mymarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.mymarket.entities.Product;
import ru.khantemirov.mymarket.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<Product> findAllProducts(){
        return cartService.findAll();
    }

    @GetMapping("/{id}")
    public void addProductToCart(@PathVariable long id){
        cartService.addProduct(id);
    }

}

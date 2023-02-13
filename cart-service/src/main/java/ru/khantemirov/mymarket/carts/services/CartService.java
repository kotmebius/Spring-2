package ru.khantemirov.mymarket.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.khantemirov.mymarket.api.ProductDto;
import ru.khantemirov.mymarket.api.ResourceNotFoundException;
import ru.khantemirov.mymarket.carts.integrations.ProductServiceIntegration;
import ru.khantemirov.mymarket.carts.model.Cart;
import ru.khantemirov.mymarket.carts.model.CartItem;


import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    private final ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    public void init(){
        cart = new Cart();
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public List< CartItem> getCartItems(){
        return cart.getItems();
    }

    public void addProduct(Long id){
        ProductDto product = productServiceIntegration.getProductById(id).orElseThrow(() ->
                new ResourceNotFoundException("Не удаётся добавить продукт с id: "+id+"в корзину. Продукт не найден"));
        cart.add(product);
    }

    public void deleteById(Long id){
        cart.deleteItem(id);
    }

    public void incCartItem(Long id){
        cart.incCartItem(id);
    }

    public void decCartItem(Long id){
        cart.decCartItem(id);
    }

    public void clearCart(){
        cart.clear();
    }

}

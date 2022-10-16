package ru.khantemirov.mymarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.khantemirov.mymarket.dtos.Cart;
import ru.khantemirov.mymarket.dtos.CartItem;
import ru.khantemirov.mymarket.entities.Product;
import ru.khantemirov.mymarket.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    private final ProductService productService;

    @PostConstruct
    public void init(){
        cart = new Cart();
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public List<CartItem> getCartItems(){
        return cart.getItems();
    }

    public void addProduct(Long id){
        Product product = productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Не удаётся добавить продукт с id: "+id+"в корзину. Продукт не найден"));
        cart.add(product);
    }
}

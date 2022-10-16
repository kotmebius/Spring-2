package ru.khantemirov.mymarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.khantemirov.mymarket.entities.Cart;
import ru.khantemirov.mymarket.entities.Product;
import ru.khantemirov.mymarket.repositories.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    public List<Product> findAll(){
        return cartRepository.findAll();
    }

    public void addProduct(Long id){
        Product product = productService.findById(id).orElseThrow();
        cartRepository.addProduct(product);
    }
}

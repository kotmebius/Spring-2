package ru.khantemirov.mymarket.repositories;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.khantemirov.mymarket.entities.Cart;
import ru.khantemirov.mymarket.entities.Product;

import java.util.List;

@Repository
@NoArgsConstructor
public class CartRepository {
    @Autowired
    private Cart cart;

    public List<Product> findAll(){
        return cart.show();
    }

    public void addProduct(Product product){
        cart.add(product);
    }
}

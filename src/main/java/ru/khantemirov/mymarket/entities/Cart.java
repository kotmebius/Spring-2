package ru.khantemirov.mymarket.entities;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Scope("singleton")
@Component
@NoArgsConstructor
public class Cart {
    private List<Product> cart = new ArrayList<Product>();

    public void add(Product product) {
        cart.add(product);
    }

    public List<Product> show(){
        return this.cart;
    }
}



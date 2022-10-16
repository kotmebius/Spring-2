package ru.khantemirov.mymarket.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.khantemirov.mymarket.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public void add(Product product) {
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    public List<CartItem> getItems(){
        return Collections.unmodifiableList(items);
    }
    private void recalculate(){
        totalPrice=0;
        for(CartItem item: items){
            totalPrice+=item.getPrice();
        }
    }

    public Cart() {
        this.items = new ArrayList<>();
    }
}



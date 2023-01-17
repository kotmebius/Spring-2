package ru.khantemirov.mymarket.model;

import lombok.Data;
import ru.khantemirov.mymarket.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public void add(Product product) {
        for (CartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void deleteItem(Long id) {
        if (items.removeIf(item -> item.getProductId().equals(id))) {
            recalculate();
        }
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void incCartItem(Long id) {
        for (CartItem item : items) {
            if (item.getProductId().equals(id)) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
    }

    public void decCartItem(Long id) {
        for (CartItem item : items) {
            if (item.getProductId().equals(id)) {
                if (item.getQuantity() > 1) {
                    item.changeQuantity(-1);
                    recalculate();
                } else {
                    deleteItem(id);
                }
                recalculate();
                return;
            }
        }
    }

    public Cart() {
        this.items = new ArrayList<>();
    }
}



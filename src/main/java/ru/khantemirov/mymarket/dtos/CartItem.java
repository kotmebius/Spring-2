package ru.khantemirov.mymarket.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public void incQuantity() {
        quantity++;
        recalculate();
    }

    public void decQuantity() {
        quantity--;
        recalculate();
    }

    public void recalculate() {
        price = pricePerProduct*quantity;
    }
}

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
        int productPosition=findCartItem(product.getId());
        if (productPosition == -1) {
            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        } else{
            items.get(productPosition).incQuantity();
        }
        recalculate();
    }

    public List<CartItem> getItems(){
        return Collections.unmodifiableList(items);
    }

    public void deleteItem(Long id){
        items.remove(findCartItem(id));
        recalculate();
    }

    private void recalculate(){
        totalPrice=0;
        for(CartItem item: items){
            totalPrice+=item.getPrice();
        }
    }

    public int findCartItem(Long productId){
        for (int i=0; i<items.size(); i++) {
            if (items.get(i).getProductId()==productId){
                return i;
            }
        }
        return -1;
    }

    public void incCartItem (Long id){
        items.get(findCartItem(id)).incQuantity();
        recalculate();
    }

    public void decCartItem (Long id){
        int quantity = items.get(findCartItem(id)).getQuantity();
        if (quantity >1){
            items.get(findCartItem(id)).decQuantity();
            recalculate();
        }else {
            deleteItem(id);
        }
    }

    public Cart() {
        this.items = new ArrayList<>();
    }
}



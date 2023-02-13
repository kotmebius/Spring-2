package ru.khantemirov.mymarket.carts.converters;

import org.springframework.stereotype.Component;
import ru.khantemirov.mymarket.api.CartItemDto;
import ru.khantemirov.mymarket.carts.model.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setProductId(cartItem.getProductId());
        return cartItemDto;
    }
}

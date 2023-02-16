package ru.khantemirov.mymarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khantemirov.mymarket.api.CartDto;
import ru.khantemirov.mymarket.api.ResourceNotFoundException;
import ru.khantemirov.mymarket.api.UserDto;
import ru.khantemirov.mymarket.core.entities.Order;
import ru.khantemirov.mymarket.core.entities.OrderItem;
import ru.khantemirov.mymarket.core.entities.User;
import ru.khantemirov.mymarket.core.integrations.CartServiceIntegration;
import ru.khantemirov.mymarket.core.repositories.OrderItemRepository;
import ru.khantemirov.mymarket.core.repositories.OrderRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private Order order;

    @Transactional
    public void createOrder(User user, UserDto customer) {
        CartDto cartDto = new CartDto();
        cartDto = cartServiceIntegration.getCurrentCart().orElseThrow(() ->
                new ResourceNotFoundException("Не удаётся найти текущую корзину"));

        order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(cartItem -> new OrderItem(
                        productService.findById(cartItem.getProductId()).orElseThrow(() ->
                                new ResourceNotFoundException("Не удаётся найти cartItem")),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()
                )
        ).collect(Collectors.toList()));

        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        orderRepository.save(order);
        cartServiceIntegration.clearCart();
    }

    public Order showOrder() {
        return new Order();
    }
}


package ru.khantemirov.mymarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.khantemirov.mymarket.dtos.Customer;
import ru.khantemirov.mymarket.entities.Order;
import ru.khantemirov.mymarket.entities.OrderItem;
import ru.khantemirov.mymarket.entities.User;
import ru.khantemirov.mymarket.exceptions.ResourceNotFoundException;
import ru.khantemirov.mymarket.model.Cart;
import ru.khantemirov.mymarket.model.CartItem;
import ru.khantemirov.mymarket.repositories.OrderItemRepository;
import ru.khantemirov.mymarket.repositories.OrderRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private Order order;


    public void createOrder(User user, Customer customer) {
        order = new Order();
        order.setUser(user);
        order.setAddress(customer.getAddress());
        order.setPhone(customer.getPhone());
        orderRepository.save(order);
        Cart cart = cartService.getCurrentCart();
        List<OrderItem> orderItems = new ArrayList<>();

//        List<OrderItem> orderItems = (cart.getItems().stream().map(cartItem ->
//                new OrderItem(order.getId(), productService.findById(cartItem.getProductId()).orElseThrow(() ->
//                        new ResourceNotFoundException("Не удаётся добавить продукт с id: " + cartItem.getProductId() + "в заказ. Продукт не найден")),
//                        order, cartItem.getQuantity(), cartItem.getPricePerProduct(),
//                        cartItem.getPrice(), null, null)
//        ).collect(Collectors.toList()));
//        orderItemRepository.saveAll(orderItems);


        for (CartItem cartItem: cart.getItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(productService.findById(cartItem.getProductId()).orElseThrow(() ->
                    new ResourceNotFoundException("Не удаётся добавить продукт с id: " + cartItem.getProductId() + "в заказ. Продукт не найден")));
            orderItem.setOrder(order);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPricePerProduct(cartItem.getPricePerProduct());
            orderItem.setPrice(cartItem.getPrice());

//            OrderItem orderItem = new OrderItem(productService.findById(cartItem.getProductId()).orElseThrow(() ->
//                        new ResourceNotFoundException("Не удаётся добавить продукт с id: " + cartItem.getProductId() + "в заказ. Продукт не найден")),
//                        order, cartItem.getQuantity(), cartItem.getPricePerProduct(),
//                        cartItem.getPrice());
            orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
        }


        order.setTotalPrice(cart.getTotalPrice());
        order.setItems(orderItems);
        orderRepository.save(order);
    }

    public Order showOrder() {
        return new Order();
    }
}


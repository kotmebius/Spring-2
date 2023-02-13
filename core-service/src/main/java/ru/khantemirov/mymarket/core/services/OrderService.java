package ru.khantemirov.mymarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.khantemirov.mymarket.api.UserDto;
import ru.khantemirov.mymarket.core.entities.Order;
import ru.khantemirov.mymarket.core.entities.User;
import ru.khantemirov.mymarket.core.repositories.OrderItemRepository;
import ru.khantemirov.mymarket.core.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
//    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private Order order;


    public void createOrder(User user, UserDto customer) {
//        order = new Order();
//        order.setUser(user);
//        order.setAddress(customer.getAddress());
//        order.setPhone(customer.getPhone());
//        orderRepository.save(order);
//        CartDto cart = cartService.getCurrentCart();
//        List<OrderItem> orderItems = new ArrayList<>();
//
//
//        for (CartItemDto cartItem: cart.getItems()) {
//            OrderItem orderItem = new OrderItem();
//
//            orderItem.setProduct(productService.findById(cartItem.getProductId()).orElseThrow(() ->
//                    new ResourceNotFoundException("Не удаётся добавить продукт с id: " + cartItem.getProductId() + "в заказ. Продукт не найден")));
//            orderItem.setOrder(order);
//            orderItem.setQuantity(cartItem.getQuantity());
//            orderItem.setPricePerProduct(cartItem.getPricePerProduct());
//            orderItem.setPrice(cartItem.getPrice());
//
//            orderItemRepository.save(orderItem);
//            orderItems.add(orderItem);
//        }
//
//
//        order.setTotalPrice(cart.getTotalPrice());
//        order.setItems(orderItems);
//        orderRepository.save(order);
    }

    public Order showOrder() {
        return new Order();
    }
}


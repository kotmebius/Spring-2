package ru.khantemirov.mymarket.core.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.khantemirov.mymarket.api.ResourceNotFoundException;
import ru.khantemirov.mymarket.api.UserDto;
import ru.khantemirov.mymarket.core.entities.Order;
import ru.khantemirov.mymarket.core.entities.User;
import ru.khantemirov.mymarket.core.services.OrderService;
import ru.khantemirov.mymarket.core.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody UserDto customer){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        orderService.createOrder(user, customer);

    }

    @GetMapping("/show")
    public Order showOrder(){
        return orderService.showOrder();
    }
}

package ru.khantemirov.mymarket.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.khantemirov.mymarket.entities.OrderItem;
import ru.khantemirov.mymarket.entities.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private UserDto userdto;
    private List<OrderItemDto> items;
    private String address;
    private String phone;
    private int totalPrice;
}

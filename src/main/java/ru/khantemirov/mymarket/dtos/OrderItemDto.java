package ru.khantemirov.mymarket.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.khantemirov.mymarket.entities.Order;
import ru.khantemirov.mymarket.entities.Product;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    private ProductDto productDto;
    private OrderDto orderDto;
    private int quantity;
    private int pricePerProduct;
    private int price;

}


package ru.khantemirov.mymarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khantemirov.mymarket.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

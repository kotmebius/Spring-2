package ru.khantemirov.mymarket.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khantemirov.mymarket.core.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

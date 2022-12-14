package ru.khantemirov.mymarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khantemirov.mymarket.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

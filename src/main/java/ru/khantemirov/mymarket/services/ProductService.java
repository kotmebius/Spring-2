package ru.khantemirov.mymarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.khantemirov.mymarket.entities.Product;
import ru.khantemirov.mymarket.repositories.ProductRepository;
import ru.khantemirov.mymarket.soap.products.ProductSOAP;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List <Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public static final Function<Product, ProductSOAP> functionProductToXML = pr -> {
        ProductSOAP productSOAP = new ProductSOAP();
        productSOAP.setId(pr.getId());
        productSOAP.setTitle(pr.getTitle());
        productSOAP.setPrice(pr.getPrice());
        return productSOAP;
    };

    public List<ProductSOAP> getAllProductsXML() {
        return productRepository.findAll().stream().map(functionProductToXML).collect(Collectors.toList());
    }

    public ProductSOAP getByIdXML(Long id) {
        return productRepository.findById(id).map(functionProductToXML).get();
    }


}

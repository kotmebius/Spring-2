package ru.khantemirov.mymarket.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.khantemirov.mymarket.core.entities.Category;
import ru.khantemirov.mymarket.core.repositories.CategoryRepository;


import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List <Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByTitle (String title){
        return categoryRepository.findByTitle(title);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}

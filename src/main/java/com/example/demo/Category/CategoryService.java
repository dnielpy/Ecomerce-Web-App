package com.example.demo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO createCategory(String name, char gender) {
        CategoryEntity new_category = categoryRepository.findByName(name);
        if (new_category == null) {
            new_category = new CategoryEntity(name, gender);
            categoryRepository.save(new_category);
            return new CategoryDTO(new_category.getName(), new_category.getGender());
        } else {
            throw new IllegalArgumentException("La categoría ya existe en la base de datos");
        }
    }

    public CategoryDTO getCategory(String name) {
        CategoryEntity category = categoryRepository.findByName(name);
        if (category != null) {
            return new CategoryDTO(category.getName(), category.getGender());
        } else {
            throw new IllegalArgumentException("La categoría no existe en la base de datos");
        }
    }

    public CategoryDTO updateCategory(String name, String newName, char newGender) {
        CategoryEntity category = categoryRepository.findByName(name);
        if (category != null) {
            if (categoryRepository.findByName(newName) != null) {
                throw new IllegalArgumentException("Ya existe una categoría con ese nombre en la base de datos");
            } else {
                category.setName(newName);
                category.setGender(newGender);
                categoryRepository.save(category);
                return new CategoryDTO(newName, newGender);
            }
        } else {
            throw new IllegalArgumentException("La categoría no existe en la base de datos");
        }
    }

    public void deleteCategory(String name) {
        CategoryEntity category = categoryRepository.findByName(name);
        if (category != null) {
            categoryRepository.delete(category);
        } else {
            throw new IllegalArgumentException("La categoría no existe en la base de datos");
        }
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }
}
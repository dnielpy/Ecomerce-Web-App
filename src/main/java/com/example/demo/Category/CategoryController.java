package com.example.demo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@RequestParam String name, @RequestParam char gender) {
        try {
            CategoryDTO categoryDTO = categoryService.createCategory(name, gender);
            return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable String name) {
        try {
            CategoryDTO categoryDTO = categoryService.getCategory(name);
            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable String name, @RequestParam String newName, @RequestParam char newGender) {
        try {
            CategoryDTO categoryDTO = categoryService.updateCategory(name, newName, newGender);
            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable String name) {
        try {
            categoryService.deleteCategory(name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
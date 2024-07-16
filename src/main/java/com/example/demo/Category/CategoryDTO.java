package com.example.demo.Category;

public class CategoryDTO {
    private String name;
    private char gender;

    public CategoryDTO(String name, char gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }
}
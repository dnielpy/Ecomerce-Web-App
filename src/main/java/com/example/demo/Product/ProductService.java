package com.example.demo.Product;

import com.example.demo.Category.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Create
    public ProductDTO createProduct(String name, double cost, String longDescription, String shortDescription, long stock, String image, CategoryEntity category) {
        ProductEntity new_product = productRepository.findByName(name.toLowerCase(Locale.ROOT));
        if (new_product == null) {
            new_product = new ProductEntity(name.toLowerCase(Locale.ROOT), cost, longDescription, shortDescription, stock, image, category);
            productRepository.save(new_product);
            return new_product.toDto();
        } else {
            throw new IllegalArgumentException("El producto ya existe en la base de datos");
        }
    }

    //Get
    public ProductDTO getProduct(String name) {
        ProductEntity new_product = productRepository.findByName(name.toLowerCase(Locale.ROOT));
        if (new_product != null) {
            return new_product.toDto();
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }

    //getAllProducts
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> all_products_entitys = productRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();
        for (ProductEntity allProductsEntity : all_products_entitys) {
            products.add(allProductsEntity.toDto());
        }
        return products;
    }

    //getProductsByGender
    public List<ProductDTO> getProductsByCategory(char gender) {
        List<ProductEntity> products_entity = productRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();
        for (ProductEntity productEntity : products_entity) {
            if (productEntity.getCategory().getGender() == gender) {
                products.add(productEntity.toDto());
            }
        }
        return products;
    }

    //getProductByRange
    public List<ProductDTO> getProductByRange(double min, double max) {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductEntity product : productEntities) {
            if (product.getCost() >= min && product.getCost() <= max) {
                productDTOS.add(product.toDto());
            }
        }
        return productDTOS;
    }

    //Update
    public ProductDTO updateProducts(String name, String new_name, double new_cost, String new_longDescription, String new_shortDescription, long new_stock, String new_image, CategoryEntity new_category) {
        ProductEntity product = productRepository.findByName(name.toLowerCase(Locale.ROOT));
        if (product != null) {
            if (productRepository.findByName(new_name) != null) {
                throw new IllegalArgumentException("Ya existe un producto con ese nombre en la base de datos");
            } else {
                product.setName(new_name);
                product.setCost(new_cost);
                product.setLongDescription(new_longDescription);
                product.setShortDescription(new_shortDescription);
                product.setStock(new_stock);
                product.setImageUrl(new_image);
                product.setCategory(new_category);
                productRepository.save(product);
                return product.toDto();
            }
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }

    //Update Stock
    public ProductDTO updateProductsStock(String name, long new_stock) {
        ProductEntity product = productRepository.findByName(name.toLowerCase(Locale.ROOT));
        if (product != null) {
            product.setStock(new_stock);
            productRepository.save(product);
            return product.toDto();
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }

    //Delete
    public ProductDTO deleteProduct(String name) {
        ProductEntity new_product = productRepository.findByName(name.toLowerCase(Locale.ROOT));
        if (new_product != null) {
            productRepository.deleteById(new_product.getId());
            return null;
            //En el controlador controllar cuando mande null
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }

    public List<ProductDTO> getNineRandomProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            productDTOs.add(productEntities.get(i).toDto());

        }
        Collections.shuffle(productDTOs);
        return productDTOs;
    }
}
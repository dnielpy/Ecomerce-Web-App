package com.example.demo.Product;

import com.example.demo.Category.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String getProduct(@RequestParam String name, Model model) {
        ProductDTO product = productService.getProduct(name);
        model.addAttribute("product", product);
        return "product";
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestParam String name, @RequestParam double cost, @RequestParam String longDescription, @RequestParam String shortDescription, @RequestParam long stock, @RequestParam String image_path, @RequestParam CategoryEntity category) {
        try {
            ProductDTO productDTO = productService.createProduct(name, cost, longDescription, shortDescription, stock, image_path, category);
            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    //GET
    @GetMapping("/{name}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String name) {
        try {
            ProductDTO productDTO = productService.getProduct(name);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<ProductDTO>> getProductsByGender(@PathVariable char gender) {
        try {
            List<ProductDTO> productDTOs = productService.getProductsByCategory(gender);
            return new ResponseEntity<>(productDTOs, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String name, @RequestParam String new_name, @RequestParam double new_cost, @RequestParam String new_longDescription, @RequestParam String new_shortDescription, @RequestParam long new_stock, @RequestParam String new_image, @RequestParam CategoryEntity new_category) {
        try {
            ProductDTO productDTO = productService.updateProducts(name, new_name, new_cost, new_longDescription, new_shortDescription, new_stock, new_image, new_category);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable String name) {
        try {
            ProductDTO productDTO = productService.deleteProduct(name);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{name}/updateStock")
    public ResponseEntity<ProductDTO> updateProductStock(@PathVariable String name, @RequestParam long new_stock) {
        try {
            ProductDTO productDTO = productService.updateProductsStock(name, new_stock);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
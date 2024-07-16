package com.example.demo.Cart;

import com.example.demo.Product.ProductDTO;
import com.example.demo.Product.ProductEntity;
import com.example.demo.Product.ProductRepository;
import com.example.demo.User.UserDTO;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;

    @PostMapping
    public ResponseEntity<CartEntity> createCart(@RequestBody UserDTO userDTO) {
        try {
            CartEntity cartEntity = cartService.createCart(userDTO);
            return new ResponseEntity<>(cartEntity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addToCart")
    public ResponseEntity<List<Long>> addToCart(@RequestParam String email, @RequestParam String product_name) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            UserDTO userDTO = userEntity.toDto();
            ProductEntity productEntity = productRepository.findByName(product_name);
            ProductDTO productDTO = productEntity.toDto();
            CartEntity cartEntity = cartService.addToCart(userDTO, productDTO);
            return new ResponseEntity<>(cartEntity.getProducts(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<List<Long>> removeFromCart(@RequestParam String email, @RequestParam long product_id) {
        try {
            return new ResponseEntity<>(cartService.removeFromCart(email, product_id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/shop")
    public String showShopPage(@RequestParam String email, Model model) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null) {
            CartEntity cartEntity = cartRepository.findByEmail(userEntity.getEmail());
            if (cartEntity != null) {
                model.addAttribute("cartItems", cartEntity.getProducts());
            }
        }
        return "shop";
    }
}
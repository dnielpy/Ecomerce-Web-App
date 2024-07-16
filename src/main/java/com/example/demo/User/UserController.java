package com.example.demo.User;

import com.example.demo.Cart.CartRepository;
import com.example.demo.Order.OrderDTO;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private static UserService userService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public static ResponseEntity<UserDTO> createUser(@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String country, @RequestParam String city, @RequestParam String address, @RequestParam String tel, @RequestParam String mobile, @RequestParam String password) {
        try {
            UserDTO userDTO = userService.createUser(email, firstName, lastName, country, city, address, tel, mobile, password);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email) {
        try {
            UserDTO userDTO = userService.getUser(email);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String email, @RequestParam String new_email, @RequestParam String new_firstName, @RequestParam String new_lastName, @RequestParam String new_country, @RequestParam String new_city, @RequestParam String new_address, @RequestParam String new_tel, @RequestParam String new_mobile) {
        try {
            UserDTO userDTO = userService.updateUser(email, new_email, new_firstName, new_lastName, new_country, new_city, new_address, new_tel, new_mobile);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        try {
            userService.deleteUser(email);
            return new ResponseEntity<>("Elimination satisfactory", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{email}/buy")
    public ResponseEntity<OrderDTO> buy(@PathVariable String email) {
        try {
            UserDTO userDTO = userService.getUser(email);
            OrderDTO orderDTO = userService.buy(userDTO, cartRepository.findByEmail(email));
            return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

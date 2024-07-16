package com.example.demo.Order;

import com.example.demo.User.UserDTO;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody UserDTO userDTO, double total, String date, String paymentMethod, String status, String productName) {
        OrderDTO result = orderService.createOrder(userDTO, total, date, paymentMethod, status, productName);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<OrderDTO> deleteOrder(@RequestBody UserDTO userDTO) {
        OrderDTO result = orderService.deleteOrder(userDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> result = orderService.getAllOrders();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@RequestBody UserDTO userDTO) {
        List<OrderDTO> result = orderService.getOrdersByUser(userDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<OrderDTO>> getOrdersByDate(@RequestParam String date) {
        List<OrderDTO> result = orderService.getOrdersByDate(date);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user-date")
    public ResponseEntity<List<OrderDTO>> getOrdersByUserAndDate(@RequestParam String email, @RequestParam String date) {
        UserEntity user = userRepository.findByEmail(email);
        UserDTO userDTO = user.toDto();
        List<OrderDTO> result = orderService.getOrdersByUserAndDate(userDTO, date);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<List<OrderDTO>> getOrdersByTotal(@RequestParam double total) {
        List<OrderDTO> result = orderService.getOrdersByTotal(total);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/range")
    public ResponseEntity<List<OrderDTO>> getOrderByRange(@RequestParam double min, @RequestParam double max) {
        List<OrderDTO> result = orderService.getOrderByRange(min, max);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
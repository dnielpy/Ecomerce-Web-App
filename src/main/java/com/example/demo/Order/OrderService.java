package com.example.demo.Order;

import com.example.demo.User.UserDTO;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    //Create
    public OrderDTO createOrder(UserDTO userDTO, double total, String date, String paymentMethod, String status, String productName) {

        UserEntity user = userRepository.findByEmail(userDTO.getEmail());

        OrderEntity new_order = new OrderEntity(user, date, total, paymentMethod, status, productName);
        orderRepository.save(new_order);
        return new OrderDTO(new_order.getUser().getEmail(), total, date, paymentMethod, status, productName);
    }

    //Delete
    public OrderDTO deleteOrder(UserDTO userDTO) {
        OrderEntity order = orderRepository.findByUserEmail(userDTO.getEmail());
        if (order != null) {
            orderRepository.deleteById(order.getId());
            return null;
        } else {
            throw new IllegalArgumentException("El usuario no tiene ordenes registradas");
        }
    }

    //getOrders
    public List<OrderDTO> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (OrderEntity order : orders) {
            OrderDTO orderDTO_temp = new OrderDTO(order.getUser().getEmail(), order.getAmount(), order.getDate(), order.getPaymentMethod(), order.getStatus(), order.getProductName());
            orderDTOS.add(orderDTO_temp);
        }
        return orderDTOS;
    }

    public List<OrderDTO> getOrdersByUser(UserDTO userDTO) {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (OrderEntity order : orders) {
            if (order.getUser().getEmail().equals(userDTO.getEmail())) {
                OrderDTO orderDTO_temp = new OrderDTO(order.getUser().getEmail(), order.getAmount(), order.getDate(), order.getPaymentMethod(), order.getStatus(), order.getProductName());
                orderDTOS.add(orderDTO_temp);
            }
        }
        return orderDTOS;
    }

    public List<OrderDTO> getOrdersByDate(String date) {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (OrderEntity order : orders) {
            if (order.getDate().equals(date)) {
                OrderDTO orderDTO_temp = new OrderDTO(order.getUser().getEmail(), order.getAmount(), order.getDate(), order.getPaymentMethod(), order.getStatus(), order.getProductName());
                orderDTOS.add(orderDTO_temp);
            }
        }
        return orderDTOS;
    }

    public List<OrderDTO> getOrdersByUserAndDate(UserDTO userDTO, String date) {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (OrderEntity order : orders) {
            if (order.getUser().getEmail().equals(userDTO.getEmail()) && order.getDate().equals(date)) {
                OrderDTO orderDTO_temp = new OrderDTO(order.getUser().getEmail(), order.getAmount(), order.getDate(), order.getPaymentMethod(), order.getStatus(), order.getProductName());
                orderDTOS.add(orderDTO_temp);
            }
        }
        return orderDTOS;
    }

    public List<OrderDTO> getOrdersByTotal(double total) {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (OrderEntity order : orders) {
            if (order.getAmount() == total) {
                OrderDTO orderDTO_temp = new OrderDTO(order.getUser().getEmail(), order.getAmount(), order.getDate(), order.getPaymentMethod(), order.getStatus(), order.getProductName());
                orderDTOS.add(orderDTO_temp);
            }
        }
        return orderDTOS;
    }

    public List<OrderDTO> getOrderByRange(double min, double max) {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (OrderEntity order : orders) {
            if (order.getAmount() >= min && order.getAmount() <= max) {
                OrderDTO orderDTO_temp = new OrderDTO(order.getUser().getEmail(), order.getAmount(), order.getDate(), order.getPaymentMethod(), order.getStatus(), order.getProductName());
                orderDTOS.add(orderDTO_temp);
            }
        }
        return orderDTOS;
    }
}
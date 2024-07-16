package com.example.demo;

import com.example.demo.Order.OrderDTO;
import com.example.demo.Order.OrderEntity;
import com.example.demo.Order.OrderRepository;
import com.example.demo.Order.OrderService;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
        // Arrange
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity(email, "firstName", "lastName", "country", "city", "address", "tel", "mobile", "password");
        when(userRepository.findByEmail(email)).thenReturn(userEntity);

        // Act
        OrderDTO result = orderService.createOrder(userEntity.toDto(), 100.0, "2022-01-01", "credit card", "processed", "testProduct");

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getUserEmail());
    }

    @Test
    public void testDeleteOrder() {
        // Arrange
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity(email, "firstName", "lastName", "country", "city", "address", "tel", "mobile", "password");
        OrderEntity orderEntity = new OrderEntity(userEntity, "2022-01-01", 100.0, "credit card", "processed", "testProduct");
        when(orderRepository.findByUserEmail(email)).thenReturn(orderEntity);

        // Act
        OrderDTO result = orderService.deleteOrder(userEntity.toDto());

        // Assert
        assertNull(result);
    }


    @Test
    public void testGetOrdersByUser() {
        // Arrange
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity(email, "firstName", "lastName", "country", "city", "address", "tel", "mobile", "password");
        lenient().when(userRepository.findByEmail(email)).thenReturn(userEntity);

        // Act
        List<OrderDTO> result = orderService.getOrdersByUser(userEntity.toDto());

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testGetOrdersByDate() {
        // Arrange
        String date = "2022-01-01";

        // Act
        List<OrderDTO> result = orderService.getOrdersByDate(date);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testGetOrdersByUserAndDate() {
        // Arrange
        String email = "test@example.com";
        String date = "2022-01-01";
        UserEntity userEntity = new UserEntity(email, "firstName", "lastName", "country", "city", "address", "tel", "mobile", "password");
        lenient().when(userRepository.findByEmail(email)).thenReturn(userEntity);

        // Act
        List<OrderDTO> result = orderService.getOrdersByUserAndDate(userEntity.toDto(), date);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testGetOrdersByTotal() {
        // Arrange
        double total = 100.0;

        // Act
        List<OrderDTO> result = orderService.getOrdersByTotal(total);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testGetOrderByRange() {
        // Arrange
        double min = 50.0;
        double max = 150.0;

        // Act
        List<OrderDTO> result = orderService.getOrderByRange(min, max);

        // Assert
        assertNotNull(result);
    }
}
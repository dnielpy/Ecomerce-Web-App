package com.example.demo;

import com.example.demo.Cart.CartRepository;
import com.example.demo.Order.OrderRepository;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.ProductService;
import com.example.demo.User.UserDTO;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import com.example.demo.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductService productService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        userService.createUser("test@test.com", "Test", "User", "Country", "City", "Address", "Tel", "Mobile", "Password");
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testGetUser() {
        // Arrange
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity(email, "firstName", "lastName", "country", "city", "address", "tel", "mobile", "password");
        when(userRepository.findByEmail(email)).thenReturn(userEntity);

        // Act
        UserDTO result = userService.getUser(email);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testGetUserNotFound() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.getUser(email));
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        String email = "test@example.com";
        String newEmail = "newtest@example.com";
        UserEntity userEntity = new UserEntity(email, "firstName", "lastName", "country", "city", "address", "tel", "mobile", "password");
        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        when(userRepository.findByEmail(newEmail)).thenReturn(null);

        // Act
        UserDTO result = userService.updateUser(email, newEmail, "newFirstName", "newLastName", "newCountry", "newCity", "newAddress", "newTel", "newMobile");

        // Assert
        assertNotNull(result);
        assertEquals(newEmail, result.getEmail());
    }

    @Test
    public void testUpdateUserNotFound() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(email, "newEmail", "newFirstName", "newLastName", "newCountry", "newCity", "newAddress", "newTel", "newMobile"));
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity(email, "firstName", "lastName", "country", "city", "address", "tel", "mobile", "password");
        when(userRepository.findByEmail(email)).thenReturn(userEntity);

        // Act
        UserDTO result = userService.deleteUser(email);

        // Assert
        assertNull(result);
    }

    @Test
    public void testDeleteUserNotFound() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(email));
    }
}
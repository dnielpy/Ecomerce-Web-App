package com.example.demo.User;

import com.example.demo.Cart.CartEntity;
import com.example.demo.Cart.CartRepository;
import com.example.demo.Order.OrderDTO;
import com.example.demo.Order.OrderRepository;
import com.example.demo.Order.OrderService;
import com.example.demo.Product.ProductEntity;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository, ProductRepository productRepository, OrderRepository orderRepository, ProductService productService) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Create
    public UserDTO createUser(String email, String firstName, String lastName, String country, String city, String address, String tel, String mobile, String password) {
        UserEntity new_user = userRepository.findByEmail(email);
        if (new_user == null) {
            new_user = new UserEntity(email, firstName, lastName, country, city, address, tel, mobile, passwordEncoder().encode(password));
            userRepository.save(new_user);
            CartEntity cart = cartRepository.findByEmail(email);
            if (cart == null) {
                CartEntity cartEntity = new CartEntity(new_user, new ArrayList<>());
                cartRepository.save(cartEntity);
            }
            return new_user.toDto();
        } else {
            throw new IllegalArgumentException("El email ya existe en la base de datos. Seleccione otro");
        }
    }

    //Get
    public UserDTO getUser(String email) {
        UserEntity new_user = userRepository.findByEmail(email);
        if (new_user != null) {
            return new_user.toDto();
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Update
    public UserDTO updateUser(String email, String new_email, String new_firstName, String new_lastName, String new_country, String new_city, String new_address, String new_tel, String new_mobile) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            if (userRepository.findByEmail(new_email) != null) {
                throw new IllegalArgumentException("Ya existe un usuario con ese email en la base de datos");
            } else {
                user.setEmail(new_email);
                user.setFirstName(new_firstName);
                user.setLastName(new_lastName);
                user.setCountry(new_country);
                user.setCity(new_city);
                user.setAddress(new_address);
                user.setTel(new_tel);
                user.setMobile(new_mobile);
                userRepository.save(user);
                return user.toDto();
            }
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Delete
    public UserDTO deleteUser(String email) {
        UserEntity new_user = userRepository.findByEmail(email);
        if (new_user != null) {
            userRepository.delete(new_user);
            return null;
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Buy
    public OrderDTO buy(UserDTO userDTO, CartEntity cart) {
        UserEntity user = userRepository.findByEmail(userDTO.getEmail());

        List<Optional<ProductEntity>> products = new ArrayList<>();

        for (int i = 0; i < cart.getProducts().size(); i++) {
            products.add(productRepository.findById(cart.getProducts().get(i)));
        }

        //Checkear que tenga dinero para pagar
        double total_price = 0;
        for (Optional<ProductEntity> productEntity : products) {
            total_price += productEntity.get().getCost();
        }

        //Chequear que el carrito no este vacio
        if (cart.getProducts().isEmpty()) {
            throw new IllegalArgumentException("El carrito esta vacio");
        } else {
            //Actualizer el Stock -1
            for (int i = 0; i < cart.getProducts().size(); i++) {
                Optional<ProductEntity> optionalProduct = productRepository.findById(cart.getProducts().get(i));
                if (optionalProduct.isPresent()) {
                    ProductEntity product = optionalProduct.get();
                    productService.updateProductsStock(product.getName(), product.getStock() - 1);
                } else {
                    throw new IllegalArgumentException("El producto no existe en la base de datos");
                }
            }

            // Aquí va agregarlo a las ventas
            OrderService orderService = new OrderService(orderRepository, userRepository);
            return orderService.createOrder(userDTO, total_price, LocalDate.now().toString(), "paymentMethod", "status", "productName");
        }
    }
}

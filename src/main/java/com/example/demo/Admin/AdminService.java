package com.example.demo.Admin;

import com.example.demo.Product.ProductRepository;
import com.example.demo.Order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public AdminService(AdminRepository adminRepository) {
    }

    @Bean
    public PasswordEncoder adminpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Create
    public AdminDTO createAdmin(String email, String password) {
        AdminEntity new_admin = adminRepository.findByEmail(email);
        if (new_admin == null) {
            new_admin = new AdminEntity(email, adminpasswordEncoder().encode(password));
            adminRepository.save(new_admin);
            return new AdminDTO(email);
        } else {
            throw new IllegalArgumentException("El email ya existe en la base de datos. Seleccione otro");
        }
    }

    //Get
    public AdminDTO getAdmin(String email) {
        AdminEntity new_admin = adminRepository.findByEmail(email);
        if (new_admin != null) {
            return new AdminDTO(new_admin.getEmail());
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Update
    public AdminDTO updateAdmin(String email, String new_email, String new_password) {
        AdminEntity old_admin = adminRepository.findByEmail(email);
        if (old_admin != null) {
            if (adminRepository.findByEmail(new_email) != null) {
                throw new IllegalArgumentException("Ya existe un usuario con ese email en la base de datos");
            } else {
                AdminEntity new_admin = new AdminEntity(new_email, new_password);
                adminRepository.delete(old_admin);
                adminRepository.save(new_admin);
                return new AdminDTO(new_email);
            }
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Delete
    public AdminDTO deleteAdmin(String email) {
        AdminEntity new_admin = adminRepository.findByEmail(email);
        if (new_admin != null) {
            adminRepository.delete(new_admin);
            return null;
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }
}

package com.example.demo.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, String> {
    AdminEntity findByEmail(String email);
}

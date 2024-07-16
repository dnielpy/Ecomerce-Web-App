package com.example.demo.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestParam String email, @RequestParam String password) {
        try {
            AdminDTO adminDTO = adminService.createAdmin(email, password);
            return new ResponseEntity<>(adminDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable String email) {
        try {
            AdminDTO adminDTO = adminService.getAdmin(email);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable String email, @RequestParam String new_email, @RequestParam String new_password) {
        try {
            AdminDTO adminDTO = adminService.updateAdmin(email, new_email, new_password);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<AdminDTO> deleteAdmin(@PathVariable String email) {
        try {
            adminService.deleteAdmin(email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
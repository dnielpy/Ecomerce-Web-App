package com.example.demo;

import com.example.demo.Admin.AdminController;
import com.example.demo.Admin.AdminDTO;
import com.example.demo.Admin.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminTest {

    @InjectMocks
    AdminController adminController;

    @Mock
    AdminService adminService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAdmin() {
        AdminDTO adminDTO = new AdminDTO("test@test.com");
        when(adminService.createAdmin(anyString(), anyString())).thenReturn(adminDTO);

        ResponseEntity<AdminDTO> response = adminController.createAdmin("test@test.com", "password");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(adminDTO, response.getBody());
    }

    @Test
    public void testGetAdmin() {
        AdminDTO adminDTO = new AdminDTO("test@test.com");
        when(adminService.getAdmin(anyString())).thenReturn(adminDTO);

        ResponseEntity<AdminDTO> response = adminController.getAdmin("test@test.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adminDTO, response.getBody());
    }

    @Test
    public void testUpdateAdmin() {
        AdminDTO adminDTO = new AdminDTO("new@test.com");
        when(adminService.updateAdmin(anyString(), anyString(), anyString())).thenReturn(adminDTO);

        ResponseEntity<AdminDTO> response = adminController.updateAdmin("test@test.com", "new@test.com", "new_password");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adminDTO, response.getBody());
    }
//    @Test
//    public void testDeleteAdmin() {
//        doNothing().when(adminService).deleteAdmin(anyString());
//
//        ResponseEntity<AdminDTO> response = adminController.deleteAdmin("test@test.com");
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//    }
}
package com.backend.cartapp.controllers;

import com.backend.cartapp.models.dto.UserRequest;
import com.backend.cartapp.models.entities.User;
import com.backend.cartapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user")
    public List<User> list() {
        return service.findAll();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> create(@RequestBody UserRequest request){
        try{
            User u = new User();
            u.setName(request.getName());
            u.setLastname(request.getLastname());
            u.setMail(request.getMail());
            u.setPass(request.getPass());
            u.setPhone(request.getPhone());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(u));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

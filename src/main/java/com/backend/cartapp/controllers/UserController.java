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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest request) { // ⬅️ Cambiado el nombre a 'login'
        try {
            // **1. Lógica de Autenticación:**
            // Llama a un método del servicio que intenta autenticar al usuario
            // Esto debería verificar el correo y la contraseña (usando hashing)
            User authenticatedUser = service.authenticate(request.getMail(), request.getPass());

            if (authenticatedUser != null) {
                // 2. Si es exitoso, devuelve 200 OK y la información del usuario (o un token)
                return ResponseEntity.ok(authenticatedUser);
            } else {
                // 3. Si las credenciales son incorrectas, devuelve 401 Unauthorized
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
            }

        } catch (Exception e) {
            // 4. Manejo de errores internos del servidor (e.g., problemas de DB)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

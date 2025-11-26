package com.backend.cartapp.services;

import com.backend.cartapp.models.entities.User;
import com.backend.cartapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    // 1. Declaración de dependencias: Agrupadas al inicio
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    // 2. Constructor: Inyección de Dependencias
    // @Autowired es opcional aquí si es el único constructor
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        // En el registro (signup), la contraseña DEBE hashearse antes de guardarse
        user.setPass(passwordEncoder.encode(user.getPass()));
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User authenticate(String mail, String pass) {
        // 1. Busca al usuario por su correo
        Optional<User> o = repository.findByMail(mail);

        if (o.isPresent()) {
            User user = o.get();

            // 2. Compara la contraseña:
            // Usa matches para comparar el pass plano con el pass hasheado de la DB
            if (passwordEncoder.matches(pass, user.getPass())) {
                // 3. ¡Credenciales correctas!
                return user;
            }
        }

        // Si no se encuentra el usuario o la contraseña es incorrecta
        return null;
    }
}

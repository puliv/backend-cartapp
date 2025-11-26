package com.backend.cartapp.services;

import java.util.List;
import com.backend.cartapp.models.entities.User;

public interface UserService {
    List<User> findAll();
    User save(User user);
}

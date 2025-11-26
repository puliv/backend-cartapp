package com.backend.cartapp.repositories;

import com.backend.cartapp.models.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByMail(String mail);
}

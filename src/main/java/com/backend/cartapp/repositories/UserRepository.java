package com.backend.cartapp.repositories;

import com.backend.cartapp.models.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}

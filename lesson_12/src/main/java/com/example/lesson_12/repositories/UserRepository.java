package com.example.lesson_12.repositories;

import com.example.lesson_12.entities.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Integer> {
}

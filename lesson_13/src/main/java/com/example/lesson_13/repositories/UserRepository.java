package com.example.lesson_13.repositories;

import com.example.lesson_13.entities.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Integer> {
}

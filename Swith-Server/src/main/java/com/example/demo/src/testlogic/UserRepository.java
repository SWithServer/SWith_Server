package com.example.demo.src.testlogic;

import com.example.demo.src.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
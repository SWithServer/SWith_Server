package com.example.demo.src.repository;

import com.example.demo.src.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    List<Register> findByUser_UserIdxAndStatusIs(Long userIdx, Integer status);
}

package com.xgileit.hrm.persistance.repository;

import com.xgileit.hrm.persistance.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    Optional<Login> findByUserName(String username);
}

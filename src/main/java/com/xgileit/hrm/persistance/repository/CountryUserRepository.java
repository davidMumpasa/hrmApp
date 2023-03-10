package com.xgileit.hrm.persistance.repository;

import com.xgileit.hrm.persistance.entity.CountryUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryUserRepository extends JpaRepository<CountryUserMapping, Integer> {
}

package com.xgileit.hrm.persistance.repository;

import com.xgileit.hrm.persistance.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}

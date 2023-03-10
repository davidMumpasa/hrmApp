package com.xgileit.hrm.persistance.repository;

import com.xgileit.hrm.persistance.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
}

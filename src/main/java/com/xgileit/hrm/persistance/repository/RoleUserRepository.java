package com.xgileit.hrm.persistance.repository;

import com.xgileit.hrm.persistance.entity.RoleUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUserRepository extends JpaRepository<RoleUserMapping, Integer> {
}

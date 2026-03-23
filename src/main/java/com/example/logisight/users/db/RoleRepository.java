package com.example.logisight.users.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    boolean existsByName(RoleName role);

    RoleEntity findByName(RoleName name);
}

package com.lima.msagenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lima.msagenda.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
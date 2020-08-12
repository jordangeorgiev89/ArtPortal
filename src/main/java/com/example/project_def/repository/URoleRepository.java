package com.example.project_def.repository;

import com.example.project_def.model.entity.URole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URoleRepository extends JpaRepository <URole, String> {

    Optional<URole> findByAuthority(String authority);

}

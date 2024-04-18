package com.enviro.assessment.grad001.lebohangkhaeane.repository;

import com.enviro.assessment.grad001.lebohangkhaeane.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<Files, String> {
}

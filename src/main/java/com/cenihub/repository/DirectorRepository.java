package com.cenihub.repository;

import com.cenihub.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    Optional<Director> findByName(String name);
    List<Director> findByNationality(String nationality);
    List<Director> findByBirthYear(Integer birthYear);
    boolean existsByName(String name);
}
package com.example.datajpaa.repository;

import com.example.datajpaa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamJpaRepository extends JpaRepository<Team, Long> {
}

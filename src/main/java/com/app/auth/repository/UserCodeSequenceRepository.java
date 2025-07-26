package com.app.auth.repository;

import com.app.auth.entity.UserCodeSequence;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCodeSequenceRepository extends JpaRepository<UserCodeSequence, Long> {
    Optional<UserCodeSequence> findByYearAndMonth(int year, int month);
}
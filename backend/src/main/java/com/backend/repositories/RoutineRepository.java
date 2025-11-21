package com.backend.repositories;

import com.backend.entities.Routine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findByPlace_PlaceIdOrderByOrderIndexAsc(Long placeId);
}

package com.fitness.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.tracker.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer>{

}

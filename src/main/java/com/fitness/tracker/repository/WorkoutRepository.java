package com.fitness.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitness.tracker.entity.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer>{
	
	@Query(nativeQuery = true, value = "select SUM(calories) from workout where user_id=?1")
	public Integer getTotalCaloriesofWorkout(Integer userid);
	

}

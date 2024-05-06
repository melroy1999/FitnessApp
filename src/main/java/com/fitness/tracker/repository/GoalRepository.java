package com.fitness.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fitness.tracker.entity.Goal;

public interface GoalRepository extends JpaRepository<Goal, Integer>{
	
	@Query(nativeQuery = true, value = "select goal_target from Goal where user_id=?1")
	public Integer getGoalTarget(Integer userId);

}

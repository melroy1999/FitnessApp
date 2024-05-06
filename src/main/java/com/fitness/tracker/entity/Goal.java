package com.fitness.tracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Goal")
public class Goal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer goalId;
	
	private String currentActivityLevel;
	
	private Integer weight;
	
	private Integer height;
	   
	private String goal;
	
	private String difficulty;
	
	private Integer goalTarget;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Goal() {
	}
	

	public Goal(Integer goalId, String currentActivityLevel, Integer weight, Integer height, String goal,
			String difficulty, Integer goalTarget, User user) {
		this.goalId = goalId;
		this.currentActivityLevel = currentActivityLevel;
		this.weight = weight;
		this.height = height;
		this.goal = goal;
		this.difficulty = difficulty;
		this.goalTarget = goalTarget;
		this.user = user;
	}


	public Integer getGoalId() {
		return goalId;
	}

	public void setGoalId(Integer goalId) {
		this.goalId = goalId;
	}

	public String getCurrentActivityLevel() {
		return currentActivityLevel;
	}

	public void setCurrentActivityLevel(String currentActivityLevel) {
		this.currentActivityLevel = currentActivityLevel;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Integer getGoalTarget() {
		return goalTarget;
	}


	public void setGoalTarget(Integer goalTarget) {
		this.goalTarget = goalTarget;
	}
	
	
	
	

}

package com.fitness.tracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Workout {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long workoutId;
	
	private String workoutType;
	
	private long kms;
	
	private long duration;
	
	private long calories;
	
	private long intensity;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Workout() {
	}

	public Workout(long workoutId, String workoutType, long kms, long duration, long calories, long intensity,
			User user) {
		this.workoutId = workoutId;
		this.workoutType = workoutType;
		this.kms = kms;
		this.duration = duration;
		this.calories = calories;
		this.intensity = intensity;
		this.user = user;
	}

	public long getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(long workoutId) {
		this.workoutId = workoutId;
	}

	public String getWorkoutType() {
		return workoutType;
	}

	public void setWorkoutType(String workoutType) {
		this.workoutType = workoutType;
	}

	public long getKms() {
		return kms;
	}

	public void setKms(long kms) {
		this.kms = kms;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getCalories() {
		return calories;
	}

	public void setCalories(long calories) {
		this.calories = calories;
	}

	public long getIntensity() {
		return intensity;
	}

	public void setIntensity(long intensity) {
		this.intensity = intensity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
		

}

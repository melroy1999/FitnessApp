package com.fitness.tracker.entity;

import java.util.List;

public class DeviceData {
	
	private String name;
	
    private List<Workout> workout;

	public DeviceData() {
	}
	



	public DeviceData(String name, List<Workout> workout) {
		this.name = name;
		this.workout = workout;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public List<Workout> getWorkout() {
		return workout;
	}



	public void setWorkout(List<Workout> workout) {
		this.workout = workout;
	}

	
    
    

}

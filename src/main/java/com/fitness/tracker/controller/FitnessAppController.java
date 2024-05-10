package com.fitness.tracker.controller;


import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.tracker.entity.DeviceData;
import com.fitness.tracker.entity.Exercise;
import com.fitness.tracker.entity.Goal;
import com.fitness.tracker.entity.LoginDetails;
import com.fitness.tracker.entity.User;
import com.fitness.tracker.entity.Workout;import com.fitness.tracker.repository.ExerciseRepository;
import com.fitness.tracker.service.FitnessTrackerService;

import jakarta.websocket.server.PathParam;

@RestController
public class FitnessAppController {
	
	@Autowired
	FitnessTrackerService fitnessTrackerService;
	
	@PostMapping("/login")
	public Integer loginUser(@RequestBody LoginDetails loginDetails) {
		return fitnessTrackerService.loginUser(loginDetails).getUserId();
	}
	
	@PostMapping("/register")
	public Integer registerUser(@RequestBody User user) {
		return fitnessTrackerService.addUser(user).getUserId();
	}
	
	@PostMapping("/workout/{userId}")
	public Workout addWorkout(@RequestBody Workout workout,@PathVariable Integer userId) {
		return fitnessTrackerService.addWorkout(workout,userId);
	}
	
	@PostMapping("/goal/{userId}")
	public String addGoal(@RequestBody Goal goal ,@PathVariable Integer userId) {
		return fitnessTrackerService.addGoal(goal,userId).toString();
	}
	
	@GetMapping("/progress/{userId}")
	public String getProgress(@PathVariable Integer userId) {
		return fitnessTrackerService.getProgress(userId).toString();
	}
	
	
	@PostMapping("/workoutplan/{userId}")
	public String addWorkoutPlan(@RequestBody Exercise exercise , @PathVariable Integer userId) {
		return fitnessTrackerService.addWorkoutPlan(exercise,userId).toString();
	}
	
	@GetMapping("/workoutplans")
	public List<Exercise> getWorkoutPlans(@RequestParam String difficulty) {
		return fitnessTrackerService.getWorkoutPlans(difficulty);
	}
	
	@PostMapping("/device/{userId}")
	public Integer addDevice(@RequestBody String deviceName , @PathVariable Integer userId) {
		return fitnessTrackerService.addDevice(deviceName,userId);
	}
	
	@GetMapping("/devicedata/{userId}")
	public DeviceData getDevice(@PathVariable Integer userId) {
		return fitnessTrackerService.getDeviceData(userId);
	}
	
	

}

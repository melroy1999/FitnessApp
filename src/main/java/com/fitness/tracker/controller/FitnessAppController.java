package com.fitness.tracker.controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.tracker.entity.Goal;
import com.fitness.tracker.entity.LoginDetails;
import com.fitness.tracker.entity.User;
import com.fitness.tracker.entity.Workout;
import com.fitness.tracker.service.FitnessTrackerService;

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
	public Workout addWorkout(@RequestBody Workout workout,@RequestParam Integer userId) {
		return fitnessTrackerService.addWorkout(workout,userId);
	}
	
	@PostMapping("/goal/{userId}")
	public String addGoal(@RequestBody Goal goal ,@RequestParam Integer userId) {
		return fitnessTrackerService.addGoal(goal,userId).toString();
	}
	
	@GetMapping("/progress/{userId}")
	public String getProgress(@RequestParam Integer userId) {
		return fitnessTrackerService.getProgress(userId).toString();
	}
	

}

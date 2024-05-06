package com.fitness.tracker.service;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.tracker.entity.Goal;
import com.fitness.tracker.entity.LoginDetails;
import com.fitness.tracker.entity.User;
import com.fitness.tracker.entity.Workout;
import com.fitness.tracker.repository.GoalRepository;
import com.fitness.tracker.repository.UserRespository;
import com.fitness.tracker.repository.WorkoutRepository;

@Service
public class FitnessTrackerService {
	
	@Autowired
	UserRespository userRespository;
	
	@Autowired
	WorkoutRepository workoutRepository;
	
	@Autowired
	GoalRepository goalRepository;
	
	public User addUser(User user) {
		
		return userRespository.save(user);
		
	}
	
	public User loginUser(LoginDetails loginDetails) {
		
		User user = userRespository.findByUserName(loginDetails.getUsername());
		
		
		if(user == null) {
			System.out.println("user not found");
		}else if(!user.getPassword().equals(loginDetails.getPassword())) {
			System.out.println("Password doesnt not match");
			return null;
		}
		return user;
		
		
	}
	
	public Workout addWorkout(Workout workout, Integer userId) {
		
		Optional<User> userOptional  = userRespository.findById(userId);
		User user = userOptional.get();
		
		workout.setUser(user);
		
		return workoutRepository.save(workout);
		
	}
	
	public JSONObject addGoal(Goal goal, Integer userId) {
		
		int goalTarget =0;
		
		if(goal.getGoal().equals("weight_loss")) {
			goalTarget=500;
		}else if(goal.getGoal().equals("weight_gain")) {
			goalTarget=200;
		}else if(goal.getGoal().equals("weight_maintain")) {
			goalTarget=300;
		}
		
		Optional<User> userOptional  = userRespository.findById(userId);
		User user = userOptional.get();
		
		goal.setGoalTarget(goalTarget);
		goal.setUser(user);
		goalRepository.save(goal);
		
		JSONObject json = new JSONObject();
		json.put("goalTarget", goalTarget);
		json.put("status", true);
		
		return json;
		
	}
	
	public JSONObject getProgress(Integer userId) {
		
		Integer totalCaloriesBurned = workoutRepository.getTotalCaloriesofWorkout(userId);
		Integer goalTarget = goalRepository.getGoalTarget(userId);
		
		JSONObject json = new JSONObject();
		json.put("totalCaloriesBurned", totalCaloriesBurned);
		json.put("goalTarget", goalTarget);
		
		return json;
		
		
		
	}
	

}

package com.fitness.tracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.fitness.tracker.entity.Device;
import com.fitness.tracker.entity.DeviceData;
import com.fitness.tracker.entity.Exercise;
import com.fitness.tracker.entity.Goal;
import com.fitness.tracker.entity.LoginDetails;
import com.fitness.tracker.entity.User;
import com.fitness.tracker.entity.Workout;
import com.fitness.tracker.repository.DeviceRepository;
import com.fitness.tracker.repository.ExerciseRepository;
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
	
	@Autowired
	ExerciseRepository exerciseRepository;
	
	@Autowired
	DeviceRepository deviceRepository;
	
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
	
	public JSONObject addWorkoutPlan(Exercise exercise , Integer userId) {
		
		Optional<User> userOptional  = userRespository.findById(userId);
		User user = userOptional.get();
		
		exercise.setUser(user);
		
		exerciseRepository.save(exercise);
		
		JSONObject json = new JSONObject();
		json.put("status", true);
		
		return json;
		
		
	}
	
	public List<Exercise> getWorkoutPlans(String difficulty) {
			
			if(difficulty.equals("beginner")) {
		
			List<Exercise> beginnerExercises = new ArrayList<Exercise>();
			Exercise begexercise1 = new Exercise();
			begexercise1.setName("Dumbbell spell caster");
			begexercise1.setInstructions("Hold a dumbbell in each hand with a pronated grip. Your feet should be wide with your hips and knees extended. This will be your starting position. Begin the movement by pulling both of the dumbbells to one side next to your hip, rotating your torso. Keeping your arms straight and the dumbbells parallel to the ground, rotate your torso to swing the weights to your opposite side. Continue alternating, rotating from one side to the other until the set is complete.");
			
			Exercise begexercise2 = new Exercise();
			begexercise2.setName("Incline Hammer Curls");
			begexercise2.setInstructions("Seat yourself on an incline bench with a dumbbell in each hand. You should pressed firmly against he back with your feet together. Allow the dumbbells to hang straight down at your side, holding them with a neutral grip. This will be your starting position. Initiate the movement by flexing at the elbow, attempting to keep the upper arm stationary. Continue to the top of the movement and pause, then slowly return to the start position.");
			
			Exercise begexercise3 = new Exercise();
			begexercise3.setName("Bodyweight Flyes");
			begexercise3.setInstructions("Position two equally loaded EZ bars on the ground next to each other. Ensure they are able to roll. Assume a push-up position over the bars, supporting your weight on your toes and hands with your arms extended and body straight. Place your hands on the bars. This will be your starting position. Using a slow and controlled motion, move your hands away from the midline of your body, rolling the bars apart. Inhale during this portion of the motion. After moving the bars as far apart as you can, return to the starting position by pulling them back together. Exhale as you perform this movement.");
			
			Exercise begexercise4 = new Exercise();
			begexercise4.setName("Pull-up");
			begexercise4.setInstructions("Take a wide grip on a pull-up bar, hanging freely with your arms extended. This will be your starting position. Pull yourself up by flexing the elbows and adducting the glenohumeral joint. Do not swing or use momentum to complete the movement. Attempt to get your chin above your hands. Pause at the top of the motion before lowering yourself to the starting position.");
			
			Exercise begexercise5 = new Exercise();
			begexercise5.setName("Decline Dumbbell Triceps Extension");
			begexercise5.setInstructions("Secure your legs at the end of the decline bench and lie down with a dumbbell on each hand on top of your thighs. The palms of your hand will be facing each other. Once you are laying down, move the dumbbells in front of you at shoulder width. The palms of the hands should be facing each other and the arms should be perpendicular to the floor and fully extended. This will be your starting position. As you breathe in and you keep the upper arms stationary (and elbows in), bring the dumbbells down slowly by moving your forearms in a semicircular motion towards you until your thumbs are next to your ears. Breathe in as you perform this portion of the movement. Lift the dumbbells back to the starting position by contracting the triceps and exhaling. Repeat for the recommended amount of repetitions.  Variations: You can use an e-z bar or barbell to perform this movement. You can also perform it on a flat bench as well.");
			
			beginnerExercises.add(begexercise1);
			beginnerExercises.add(begexercise2);
			beginnerExercises.add(begexercise3);
			beginnerExercises.add(begexercise4);
			beginnerExercises.add(begexercise5);
			
			return beginnerExercises;
			
	   }else if(difficulty.equals("intermediate")) {
				
				List<Exercise> intermediateExercises = new ArrayList<Exercise>();
				Exercise intexercise1 = new Exercise();
				intexercise1.setName("Landmine twist");
				intexercise1.setInstructions("Position a bar into a landmine or securely anchor it in a corner. Load the bar to an appropriate weight. Raise the bar from the floor, taking it to shoulder height with both hands with your arms extended in front of you. Adopt a wide stance. This will be your starting position. Perform the movement by rotating the trunk and hips as you swing the weight all the way down to one side. Keep your arms extended throughout the exercise. Reverse the motion to swing the weight all the way to the opposite side. Continue alternating the movement until the set is complete.");
				
				
				Exercise intexercise2 = new Exercise();
				intexercise2.setName("Hammer Curls");
				intexercise2.setInstructions("Stand up with your torso upright and a dumbbell on each hand being held at arms length. The elbows should be close to the torso. The palms of the hands should be facing your torso. This will be your starting position. Now, while holding your upper arm stationary, exhale and curl the weight forward while contracting the biceps. Continue to raise the weight until the biceps are fully contracted and the dumbbell is at shoulder level. Hold the contracted position for a brief moment as you squeeze the biceps. Tip: Focus on keeping the elbow stationary and only moving your forearm. After the brief pause, inhale and slowly begin the lower the dumbbells back down to the starting position. Repeat for the recommended amount of repetitions.  Variations: There are many possible variations for this movement. For instance, you can perform the exercise sitting down on a bench with or without back support and you can also perform it by alternating arms; first lift the right arm for one repetition, then the left, then the right, etc.");
				
				Exercise intexercise3 = new Exercise();
				intexercise3.setName("Dumbbell Bench Press");
				intexercise3.setInstructions("Lie down on a flat bench with a dumbbell in each hand resting on top of your thighs. The palms of your hands will be facing each other. Then, using your thighs to help raise the dumbbells up, lift the dumbbells one at a time so that you can hold them in front of you at shoulder width. Once at shoulder width, rotate your wrists forward so that the palms of your hands are facing away from you. The dumbbells should be just to the sides of your chest, with your upper arm and forearm creating a 90 degree angle. Be sure to maintain full control of the dumbbells at all times. This will be your starting position. Then, as you breathe out, use your chest to push the dumbbells up. Lock your arms at the top of the lift and squeeze your chest, hold for a second and then begin coming down slowly. Tip: Ideally, lowering the weight should take about twice as long as raising it. Repeat the movement for the prescribed amount of repetitions of your training program.  Caution: When you are done, do not drop the dumbbells next to you as this is dangerous to your rotator cuff in your shoulders and others working out around you. Just lift your legs from the floor bending at the knees, twist your wrists so that the palms of your hands are facing each other and place the dumbbells on top of your thighs. When both dumbbells are touching your thighs simultaneously push your upper torso up (while pressing the dumbbells on your thighs) and also perform a slight kick forward with your legs (keeping the dumbbells on top of the thighs). By doing this combined movement, momentum will help you get back to a sitting position with both dumbbells still on top of your thighs. At this moment you can place the dumbbells on the floor. Variations: Another variation of this exercise is to perform it with the palms of the hands facing each other. Also, you can perform the exercise with the palms facing each other and then twisting the wrist as you lift the dumbbells so that at the top of the movement the palms are facing away from the body. I personally do not use this variation very often as it seems to be hard on my shoulders.");
				
				Exercise intexercise4 = new Exercise();
				intexercise4.setName("Weighted pull-up");
				intexercise4.setInstructions("Attach a weight to a dip belt and secure it around your waist. Grab the pull-up bar with the palms of your hands facing forward. For a medium grip, your hands should be spaced at shoulder width. Both arms should be extended in front of you holding the bar at the chosen grip. You'll want to bring your torso back about 30 degrees while creating a curvature in your lower back and sticking your chest out. This will be your starting position. Now, exhale and pull your torso up until your head is above your hands. Concentrate on squeezing your shoulder blades back and down as you reach the top contracted position. After a brief moment at the top contracted position, inhale and slowly lower your torso back to the starting position with your arms extended and your lats fully stretched.");
				
				Exercise intexercise5 = new Exercise();
				intexercise5.setName("Triceps dip");
				intexercise5.setInstructions("To get into the starting position, hold your body at arm's length with your arms nearly locked above the bars. Now, inhale and slowly lower yourself downward. Your torso should remain upright and your elbows should stay close to your body. This helps to better focus on tricep involvement. Lower yourself until there is a 90 degree angle formed between the upper arm and forearm. Then, exhale and push your torso back up using your triceps to bring your body back to the starting position. Repeat the movement for the prescribed amount of repetitions.  Variations: If you are new at this exercise and do not have the strength to perform it, use a dip assist machine if available. These machines use weight to help you push your bodyweight. Otherwise, a spotter holding your legs can help. More advanced lifters can add weight to the exercise by using a weight belt that allows the addition of weighted plates");
				
				intermediateExercises.add(intexercise1);
				intermediateExercises.add(intexercise2);
				intermediateExercises.add(intexercise3);
				intermediateExercises.add(intexercise4);
				intermediateExercises.add(intexercise5);
				
				return intermediateExercises;
				
		   }
	
			return null;
			
	
	
	}
	
	public Integer addDevice(String deviceName,Integer userId) {
		
		
		User user = userRespository.findById(userId).get();
		
		if(user == null) {
			System.out.println("User is null");
		}
		
		Device device = new Device();
		device.setUser(user);
		device.setName(deviceName);
		
		return deviceRepository.save(device).getDeviceId();
		
		
		
	}
	
	public DeviceData getDeviceData(Integer userId) {
		
		String name = deviceRepository.findDeviceNameByUserId(userId);
		
		List<Workout> workouts = workoutRepository.getWorkoutByUserId(userId);
		
		if(workouts==null) {
			System.out.println("Workout not found for the user");
		}
		
		DeviceData deviceData = new DeviceData();
		deviceData.setName(name);
		deviceData.setWorkout(workouts);
		
		return deviceData;
		
		
	}
	
	

}

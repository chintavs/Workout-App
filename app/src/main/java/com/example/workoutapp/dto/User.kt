package com.example.workoutapp.dto

import java.util.Date

//ADD @SERIALIZEDNAME ONCE JSON IS COMPILED
data class User(var userProfile: String, var calorieGoal: String, var workoutsCompleted: String, var friendList: Array<String>, var workoutDate: Date, var height: Double, var weight: Double, var bmi: String){

    //toString override
    override fun toString(): String {
        //Returns all but friend list
        //User: $UserProfile
        //Calorie Goal: $calorieGoal
        //Workouts Completed: $workoutsCompleted
        //Height: $height
        //Weight: $weight
        //BMI: $bmi
        return "User: $userProfile\nCalorie Goal: $calorieGoal\nWorkouts Completed: $workoutsCompleted\nHeight: $height\nWeight: $weight\nBMI: $bmi"
    }
}

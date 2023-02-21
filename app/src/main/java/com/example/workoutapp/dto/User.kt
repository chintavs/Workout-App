package com.example.workoutapp.dto

import java.util.Date

data class User(var userProfile: String, var calorieGoal: String, var workoutsCompleted: String, var friendList: Array<String>, var workoutDate: Date, var height: Double, var weight: Double, var BMI: String){

    //toString
    override fun toString(): String {
        //Returns all but friend list
        return "User: $userProfile\nCalorie Goal: $calorieGoal\nWorkouts Completed: $workoutsCompleted\nHeight: $height\nWeight: $weight\nBMI: $BMI"
    }
}

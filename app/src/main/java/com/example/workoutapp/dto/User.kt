package com.example.workoutapp.dto

import java.util.Date

//ADD @SERIALIZEDNAME ONCE JSON IS COMPILED
data class User(var firstName: String, var lastName: String, var username: String, var calorieGoal: String, var workoutsCompleted: String, var friendList: Array<String>, var workoutDate: Date, var height: Double, var weight: Double, var bmi: String){

    var fullName: String = "$firstName $lastName"

    //toString override
    override fun toString(): String {
        //Returns all but friend list
        //First name: $firstName
        //Last name: $lastName
        //Username: $username
        //Calorie Goal: $calorieGoal
        //Workouts Completed: $workoutsCompleted
        //Height: $height
        //Weight: $weight
        //BMI: $bmi
        return "Name: $firstName $lastName\nUsername: $username\nCalorie Goal: $calorieGoal\nWorkouts Completed: $workoutsCompleted\nHeight: $height\nWeight: $weight\nBMI: $bmi"
    }
}

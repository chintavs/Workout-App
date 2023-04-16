package com.example.workoutapp.dto

//ADD @SERIALIZEDNAME ONCE JSON IS COMPILED
data class User(val uid: String = "", var displayName: String?, var firstName: String, var lastName: String, var calorieGoal: String, var workoutsCompleted: String, var friendList: Array<String>, var workoutDate: String, var height: String, var weight: String, var bmi: String){

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
        return "Name: $firstName $lastName\nUsername: $displayName\nCalorie Goal: $calorieGoal\nWorkouts Completed: $workoutsCompleted\nHeight (inches): $height\nWeight (lbs): $weight\nBMI: $bmi"
    }
    //string concatenation for displaying within groups on GroupPage
    fun nameToString(): String{
        //Returns displayName, firstName lastName
        return "$displayName, $firstName $lastName"
    }
}

package com.example.workoutapp.data

data class User(
    val username: String,
    val height: String,
    val weight: String,
    val bmi: String,
    val goals: List<Goal>,
    val workouts: List<Workout>
)

data class Goal(
    val name: String,
    val progress: String
)

data class Workout(
    val name: String,
    val exercises: List<String>
)

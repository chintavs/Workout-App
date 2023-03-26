package com.example.workoutapp.data

/* This file contains three data classes: User, Goal, and Workout. User represents a user's profile information,
including their username, height, weight, BMI, a list of goals, and a list of workouts. Goal represents a user's fitness goal,
including the name of the goal and the progress made towards achieving it. Workout represents a user's workout, including
the name of the workout and a list of exercises in the workout. */

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

package com.example.workoutapp.dto

data class WorkoutRec(val name: String, val force: String, val level: String, val mechanic: String, val equipment: String,
                      val primaryMuscles: List<String>, val secondaryMuscles: List<String>, val instructions: String, val category: String) {
    override fun toString(): String {
        return name
    }
}
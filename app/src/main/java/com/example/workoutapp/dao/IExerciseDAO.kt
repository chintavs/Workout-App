package com.example.workoutapp.dao

import com.example.workoutapp.dto.WorkoutRec
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface IExerciseDAO {


    @GET("/{workout}/exercise.json")
    fun getAllExercises(@Path("workout") workout: String) : Call<ArrayList<WorkoutRec>>

}
package com.example.workoutapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.workoutapp.dto.WorkoutRec

@Dao
interface ILocalExerciseDAO {

    @Query("SELECT * FROM exercises")
    fun getExercises() : LiveData<List<WorkoutRec>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries: ArrayList<WorkoutRec>)

    @Delete
    fun delete(country : WorkoutRec)

}
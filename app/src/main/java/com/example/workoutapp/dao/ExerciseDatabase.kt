package com.example.workoutapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.workoutapp.dto.WorkoutRec

@Database(entities= arrayOf(WorkoutRec::class), version =1)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun localExerciseDAO() : ILocalExerciseDAO
}
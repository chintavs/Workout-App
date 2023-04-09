package com.example.workoutapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkoutViewModel: ViewModel() {
    private val _workoutdays = MutableLiveData<List<MyWorkoutDay>>()
    val workoutdays: LiveData<List<MyWorkoutDay>> = _workoutdays

    init {
        _workoutdays.value = listOf(
            MyWorkoutDay(
                day = "",
                exercises = listOf("")
            )
        )
    }
}
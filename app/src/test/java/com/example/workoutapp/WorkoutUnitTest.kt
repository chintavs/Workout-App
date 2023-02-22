package com.example.workoutapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import org.junit.After
import org.junit.Test


import org.junit.Assert.*
import org.junit.Before
import io.mockk.MockKAnnotations

import org.junit.Rule
import org.junit.rules.TestRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WorkoutUnitTest {

    lateinit var gam: GroupsActivity
    var allWorkouts : List<Workouts>? = ArrayList<Country>()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    lateinit var mockWorkoutService: WorkoutService

    @Before
    fun populateWorkout() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockKAnnotations.init(this)
        gam = GroupsActivity()

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `Given a dto when name is Barbell Curl, and level is beginner`() {
        var workout = Workout("Barbell Curl", "beginner")
        assertTrue(workout.name.equals("Barbell Curl"))
        assertTrue(workout.level.equals("beginner"))
    }


}
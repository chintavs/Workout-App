package com.example.workoutapp

import androidx.lifecycle.Observer
import com.example.workoutapp.dto.WorkoutRec
import com.example.workoutapp.service.WorkoutService
import org.junit.Test

import org.junit.Assert.*

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After

import org.junit.Before
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WorkoutUnitTest {

    lateinit var gam: WorkoutActivity
    lateinit var workoutService: WorkoutService
    var allWorkouts: List<WorkoutRec>? = ArrayList<WorkoutRec>()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    lateinit var mockWorkoutService: WorkoutService

    @Before
    fun populateWorkout() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockKAnnotations.init(this)
        gam = WorkoutActivity()

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
    //This test makes sure the dto is gathering the info from the json service we are using
    @Test
    fun `Given a dto when name is Barbell Curl, and level is beginner`() {
        var workout = WorkoutRec("Barbell Curl", "pull", "beginner", "isolation", "barbell", arrayOf("biceps"), arrayOf("forearms"),
            arrayOf("Stand up with your torso upright while holding a barbell at a shoulder-width grip. The palm of your hands should be facing forward and the elbows should be close to the torso. This will be your starting position.",
            "While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. Tip: Only the forearms should move.",
            "Continue the movement until your biceps are fully contracted and the bar is at shoulder level. Hold the contracted position for a second and squeeze the biceps hard.",
            "Slowly begin to bring the bar back to starting position as your breathe in.",
            "Repeat for the recommended amount of repetitions."), "strength")
        assertTrue(workout.workoutName.equals("Barbell Curl"))
        assertTrue(workout.force.equals("pull"))
        assertTrue(workout.level.equals("beginner"))
        assertTrue(workout.mechanic.equals("isolation"))
        assertTrue(workout.equipment.equals("barbell"))
        assertTrue(workout.primaryMuscles.equals("biceps"))
        assertTrue(workout.secondaryMuscles.equals("forearms"))
        assertTrue(
            workout.instructions == arrayOf("Stand up with your torso upright while holding a barbell at a shoulder-width grip. The palm of your hands should be facing forward and the elbows should be close to the torso. This will be your starting position.",
                "While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. Tip: Only the forearms should move.",
                "Continue the movement until your biceps are fully contracted and the bar is at shoulder level. Hold the contracted position for a second and squeeze the biceps hard.",
                "Slowly begin to bring the bar back to starting position as your breathe in.",
                "Repeat for the recommended amount of repetitions.")
        )
        assertTrue(workout.category.equals("strength"))
    }
    //This test checks if the workout list is compiling the information correctly
    @Test
    fun `Given a workout dto when name is Barbell Curl force is pull etc`() {
        var workout = WorkoutRec("Barbell Curl", "pull", "beginner", "isolation", "barbell", arrayOf("biceps"), arrayOf("forearms"),
            arrayOf("Stand up with your torso upright while holding a barbell at a shoulder-width grip. The palm of your hands should be facing forward and the elbows should be close to the torso. This will be your starting position.",
            "While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. Tip: Only the forearms should move.",
            "Continue the movement until your biceps are fully contracted and the bar is at shoulder level. Hold the contracted position for a second and squeeze the biceps hard.",
            "Slowly begin to bring the bar back to starting position as your breathe in.",
            "Repeat for the recommended amount of repetitions."), "strength")
        assertTrue(workout.equals("Barbell Curl pull beginner isolation barbell biceps forearms Stand up with your torso upright while holding a barbell at a shoulder-width grip. The palm of your hands should be facing forward and the elbows should be close to the torso. This will be your starting position. While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. Tip: Only the forearms should move. Continue the movement until your biceps are fully contracted and the bar is at shoulder level. Hold the contracted position for a second and squeeze the biceps hard. Slowly begin to bring the bar back to starting position as your breathe in. Repeat for the recommended amount of repetitions. strength"))
    }

    //The json service we are using has split json files we need to parse so this will likely change as
    //we research how to make it work.

    @Test
    fun `Given service connects to Countries JSON stream when data are read and parsed then workout collection should be greater than zero`() =runTest {
            launch(Dispatchers.Main) {
                givenWorkoutServiceIsInitialized()
                thenTheWorkoutCollectionSizeShouldBeGreaterThanZero()

            }


        }

    private fun givenWorkoutServiceIsInitialized() {
        workoutService = WorkoutService()
    }



    private fun thenTheWorkoutCollectionSizeShouldBeGreaterThanZero() {
        assertNotNull(allWorkouts)
        assertTrue(allWorkouts!!.isNotEmpty())
    }


}

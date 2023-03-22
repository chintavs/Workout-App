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

    lateinit var gam: GroupsActivity
    lateinit var workoutService: WorkoutService()
    var allWorkouts: List<WorkoutRec>? = ArrayList<WorkoutRec>()

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
    //This test makes sure the dto is gathering the info from the json service we are using
    @Test
    fun `Given a dto when name is Barbell Curl, and level is beginner`() {
        var workout = WorkoutRec("Barbell Curl", "pull", "beginner", "isolation", "barbell", ["biceps"], ["forearms"],
            ["Stand up with your torso upright while holding a barbell at a shoulder-width grip. The palm of your hands should be facing forward and the elbows should be close to the torso. This will be your starting position.",
            "While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. Tip: Only the forearms should move.",
            "Continue the movement until your biceps are fully contracted and the bar is at shoulder level. Hold the contracted position for a second and squeeze the biceps hard.",
            "Slowly begin to bring the bar back to starting position as your breathe in.",
            "Repeat for the recommended amount of repetitions."], "strength")
        assertTrue(workout.workoutName.equals("Barbell Curl"))
        assertTrue(workout.force.equals("pull"))
        assertTrue(workout.level.equals("beginner"))
        assertTrue(workout.mechanic.equals("isolation"))
        assertTrue(workout.equipment.equals("barbell"))
        assertTrue(workout.primaryMuscles.equals("biceps"))
        assertTrue(workout.secondaryMuscles.equals("forearms"))
        assertTrue(
            workout.instructions == ["Stand up with your torso upright while holding a barbell at a shoulder-width grip. The palm of your hands should be facing forward and the elbows should be close to the torso. This will be your starting position.",
                "While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. Tip: Only the forearms should move.",
                "Continue the movement until your biceps are fully contracted and the bar is at shoulder level. Hold the contracted position for a second and squeeze the biceps hard.",
                "Slowly begin to bring the bar back to starting position as your breathe in.",
                "Repeat for the recommended amount of repetitions."]
        )
        assertTrue(workout.category.equals("strength"))
    }
    //This test checks if the workout list is compiling the information correctly
    @Test
    fun `Given a workout dto when name is Barbell Curl force is pull etc`() {
        var workout = WorkoutRec("Barbell Curl", "pull", "beginner", "isolation", "barbell", "biceps", "forearms",
            ["Stand up with your torso upright while holding a barbell at a shoulder-width grip. The palm of your hands should be facing forward and the elbows should be close to the torso. This will be your starting position.",
            "While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. Tip: Only the forearms should move.",
            "Continue the movement until your biceps are fully contracted and the bar is at shoulder level. Hold the contracted position for a second and squeeze the biceps hard.",
            "Slowly begin to bring the bar back to starting position as your breathe in.",
            "Repeat for the recommended amount of repetitions."], "strength")
        assertTrue(workout.ToString().equals("Barbell Curl pull beginner isolation barbell biceps forearms Stand up with your torso upright while holding a barbell at a shoulder-width grip. The palm of your hands should be facing forward and the elbows should be close to the torso. This will be your starting position. While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. Tip: Only the forearms should move. Continue the movement until your biceps are fully contracted and the bar is at shoulder level. Hold the contracted position for a second and squeeze the biceps hard. Slowly begin to bring the bar back to starting position as your breathe in. Repeat for the recommended amount of repetitions. strength"))
    }

    //The json service we are using has split json files we need to parse so this will likely change as
    //we research how to make it work.

    @Test
    fun `Given service connects to Countries JSON stream when data are read and parsed then country collection should be greater than zero`() =runTest {
            launch(Dispatchers.Main) {
                givenWorkoutServiceIsInitialized()
                whenServiceDataAreReadAndParsed()
                thenTheWorkoutCollectionSizeShouldBeGreaterThanZero()

            }


        }

    private fun givenWorkoutServiceIsInitialized() {
        workoutService = WorkoutService()
    }

    private suspend fun whenServiceDataAreReadAndParsed() {
        allWorkouts = workoutService.fetchWorkouts()
    }

    private fun thenTheWorkoutCollectionSizeShouldBeGreaterThanZero() {
        assertNotNull(allWorkouts)
        assertTrue(allWorkouts!!.isNotEmpty())
    }

    private fun givenViewModelIsInitializedWithMockData() {
        val countries = ArrayList<WorkoutRec>()
        countries.add(WorkoutRec("3/4 Sit-Up", "pull", "beginner", "compound", "body only", "abdominals", "[]",
            "Lie down on the floor and secure your feet. Your legs should be bent at the knees.",
            "Place your hands behind or to the side of your head. You will begin with your back on the ground. This will be your starting position.",
            "Flex your hips and spine to raise your torso toward your knees.",
            "At the top of the contraction your torso should be perpendicular to the ground. Reverse the motion, going only Â¾ of the way down.",
            "Repeat for the recommended amount of repetitions."))
        countries.add(WorkoutRec("Barbell Curl", "pull", "beginner", "isolation", "barbell", "biceps", "forearms",
            "Stand up with your torso upright while holding a barbell at a shoulder-width grip. The palm of your hands should be facing forward and the elbows should be close to the torso. This will be your starting position.",
            "While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. Tip: Only the forearms should move.",
            "Continue the movement until your biceps are fully contracted and the bar is at shoulder level. Hold the contracted position for a second and squeeze the biceps hard.",
            "Slowly begin to bring the bar back to starting position as your breathe in.",
            "Repeat for the recommended amount of repetitions.", "strength"))
        countries.add(WorkoutRec("Ab Crunch Machine", "pull", "intermediate", "isolation", "machine",
            "[abdominals]", " ",
            "Select a light resistance and sit down on the ab machine placing your feet under the pads provided and grabbing the top handles. Your arms should be bent at a 90 degree angle as you rest the triceps on the pads provided. This will be your starting position.",
            "At the same time, begin to lift the legs up as you crunch your upper torso. Breathe out as you perform this movement. Tip: Be sure to use a slow and controlled motion. Concentrate on using your abs to move the weight while relaxing your legs and feet.",
            "After a second pause, slowly return to the starting position as you breathe in.",
            "Repeat the movement for the prescribed amount of repetitions.",
             "strength"))

        coEvery {workoutService.fetchWorkouts()} returns workouts

        gam.workoutService = mockworkoutService
    }

    private fun whenJSONDataAreReadAndParsed() {
        gam.fetchWorkouts()
    }
    private fun thenResultsShouldContainBelize() {
        var allCountries : List<Workout>? = ArrayList<Workout>()
        val latch = CountDownLatch(1);
        val observer = object : Observer<List<Workout>> {
            override fun onChanged(t: List<Workout>?) {
                allCountries = t
                latch.countDown()
                gam.workouts.removeObserver(this)
            }
        }
        gam.workouts.observeForever(observer)

        latch.await(1, TimeUnit.SECONDS)
        assertNotNull(allCountries)
        assertTrue(allCountries!!.contains(Workout("3/4 Sit-Up", "pull", "beginner", "compound", "body only", "abdominals", "[]",
            "Lie down on the floor and secure your feet. Your legs should be bent at the knees.",
            "Place your hands behind or to the side of your head. You will begin with your back on the ground. This will be your starting position.",
            "Flex your hips and spine to raise your torso toward your knees.",
            "At the top of the contraction your torso should be perpendicular to the ground. Reverse the motion, going only Â¾ of the way down.",
            "Repeat for the recommended amount of repetitions.")))

    }
}

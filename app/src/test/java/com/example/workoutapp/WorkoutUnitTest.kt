package com.example.workoutapp

import androidx.lifecycle.Observer
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
    lateinit var workoutService: WorkoutService
    var allWorkouts: List<Workout>? = ArrayList<Workout>()

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
        val workout = Workout("Barbell Curl", "pull", "beginner", "isolation", "barbell", "biceps", "forearms",
            "Example Workout Instructions.", "strength")
        assertTrue(workout.name.equals("Barbell Curl"))
        assertTrue(workout.force.equals("pull"))
        assertTrue(workout.level.equals("beginner"))
        assertTrue(workout.mechanic.equals("isolation"))
        assertTrue(workout.equipment.equals("barbell"))
        assertTrue(workout.primaryMuscles.equals("biceps"))
        assertTrue(workout.secondaryMuscles.equals("forearms"))
        assertTrue(workout.instructions.equals("Example Workout Instructions."))
        assertTrue(workout.category.equals("strength"))
    }
    //This test checks if the workout list is compiling the information correctly
    @Test
    fun `Given a workout dto when name is Barbell Curl force is pull etc`() {
        val workout = Workout("Barbell Curl", "pull", "beginner", "isolation", "barbell", "biceps", "forearms",
            "Example Workout Instructions.", "strength")
        assertTrue(workout.ToString().equals("Barbell Curl pull beginner isolation barbell biceps forearms Stand up with your torso upright while holding a barbell at a shoulder-width grip. The palm of your hands should be facing forward and the elbows should be close to the torso. This will be your starting position. While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. Tip: Only the forearms should move. Continue the movement until your biceps are fully contracted and the bar is at shoulder level. Hold the contracted position for a second and squeeze the biceps hard. Slowly begin to bring the bar back to starting position as your breathe in. Repeat for the recommended amount of repetitions. strength"))
    }
    //The json service we are using has split json files we need to parse so this will likely change as
    //we research how to make it work.
    @Test
    fun `Given service connects to Workouts JSON stream when data are read and parsed then workout collection should be greater than zero`() =runTest {
            launch(Dispatchers.Main) {
                givenWorkoutServiceIsInitialized()
                whenServiceDataAreReadAndParsed()
                thenTheWorkoutCollectionSizeShouldBeGreaterThanZero()
            }
        }

    private fun givenWorkoutServiceIsInitialized() {
        workoutService = WorkoutService()
    }

    private fun whenServiceDataAreReadAndParsed() {
        allWorkouts = workoutService.fetchWorkouts()
    }

    private fun thenTheWorkoutCollectionSizeShouldBeGreaterThanZero() {
        assertNotNull(allWorkouts)
        assertTrue(allWorkouts!!.isNotEmpty())
    }

    private fun givenViewModelIsInitializedWithMockData() {
        val workouts = ArrayList<Workout>()
        workouts.add(Workout("3/4 Sit-Up", "pull", "beginner", "compound", "body only", "abdominals", "[]",
            "Example Workout Instructions."))
        workouts.add(Workout("Barbell Curl", "pull", "beginner", "isolation", "barbell", "biceps", "forearms",
            "Example Workout Instructions.", "strength"))
        workouts.add(Workout("Ab Crunch Machine", "pull", "intermediate", "isolation", "machine",
            "[abdominals]", " ", "Example Workout Instructions.", "strength"))

        coEvery {workoutService.fetchWorkouts()} returns workouts

        gam.workoutService = mockWorkoutService
    }

    private fun whenJSONDataAreReadAndParsed() {
        gam.fetchWorkouts()
    }
    private fun thenResultsShouldContainBarbellCurl() {
        val latch = CountDownLatch(1)
        val observer = object : Observer<List<Workout>> {
            override fun onChanged(t: List<Workout>?) {
                allWorkouts = t
                latch.countDown()
                gam.workouts.removeObserver(this)
            }
        }
        gam.workouts.observeForever(observer)

        latch.await(1, TimeUnit.SECONDS)
        assertNotNull(allWorkouts)
        assertTrue(allWorkouts!!.contains(Workout("3/4 Sit-Up", "pull", "beginner", "compound", "body only", "abdominals", "[]",
            "Example Workout Instructions.")))

    }
}

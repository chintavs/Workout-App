import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.workoutapp.Goal
import com.example.workoutapp.Workout

class ProfileViewModel : ViewModel() {
    private val _workouts = MutableLiveData<List<Workout>>()
    val workouts: LiveData<List<Workout>> = _workouts

    private val _goals = MutableLiveData<List<Goal>>()
    val goals: LiveData<List<Goal>> = _goals

    init {
        // Fetch the data from the backend or database and set the values of _workouts and _goals
        _workouts.value = listOf(
            Workout(
                name = "",
                exercises = listOf("")
            )
        )

        _goals.value = listOf(
            Goal(
                name = "",
                progress = ""
            )
        )
    }
}

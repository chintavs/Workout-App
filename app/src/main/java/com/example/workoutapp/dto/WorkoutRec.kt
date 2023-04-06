package com.example.workoutapp.dto



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName="exercises")
/**
 *
 * This is a data class for Workout Records.
 *
 */
data class WorkoutRec(@SerializedName("name") var workoutName: String, val force: String, val level: String, val mechanic: String, val equipment: String,
                      val primaryMuscles: Array<String>, val secondaryMuscles: Array<String>, val instructions: Array<String>, val category: String, @PrimaryKey var id: Int = 0) {
    override fun toString(): String {
        return "$workoutName"
    }



}

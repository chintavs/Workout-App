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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WorkoutRec

        if (!primaryMuscles.contentEquals(other.primaryMuscles)) return false
        if (!secondaryMuscles.contentEquals(other.secondaryMuscles)) return false
        if (!instructions.contentEquals(other.instructions)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = primaryMuscles.contentHashCode()
        result = 31 * result + secondaryMuscles.contentHashCode()
        result = 31 * result + instructions.contentHashCode()
        return result
    }

}

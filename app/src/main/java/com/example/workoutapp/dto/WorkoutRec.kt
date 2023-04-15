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

        if (workoutName != other.workoutName) return false
        if (force != other.force) return false
        if (level != other.level) return false
        if (mechanic != other.mechanic) return false
        if (equipment != other.equipment) return false
        if (!primaryMuscles.contentEquals(other.primaryMuscles)) return false
        if (!secondaryMuscles.contentEquals(other.secondaryMuscles)) return false
        if (!instructions.contentEquals(other.instructions)) return false
        if (category != other.category) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = workoutName.hashCode()
        result = 31 * result + force.hashCode()
        result = 31 * result + level.hashCode()
        result = 31 * result + mechanic.hashCode()
        result = 31 * result + equipment.hashCode()
        result = 31 * result + primaryMuscles.contentHashCode()
        result = 31 * result + secondaryMuscles.contentHashCode()
        result = 31 * result + instructions.contentHashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + id
        return result
    }


}

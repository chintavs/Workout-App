package com.example.workoutapp

import junit.framework.TestCase.assertEquals
import org.junit.Test

class JsonUnitTest {
    @Test
    fun `Given a exercise name contained within the json then I should recieve a list of muscles that are worked out by thte exercise`() {
        givenJsonFileIsValid()
        whenExerciseDataIsReadAndParsed()
        thenExerciseCollectionShouldNotBeEmpty()
    }

    private fun thenExerciseCollectionShouldNotBeEmpty() {
        TODO("Not yet implemented")
    }

    private fun whenExerciseDataIsReadAndParsed() {
        TODO("Not yet implemented")
    }

    private fun givenJsonFileIsValid() {
        TODO("Not yet implemented")
    }

}
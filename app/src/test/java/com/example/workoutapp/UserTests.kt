package com.example.workoutapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.workoutapp.dto.User
import com.example.workoutapp.service.UserService
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.nio.file.attribute.UserPrincipalLookupService

class UserTests {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var userService : UserService
    var allUsers : List<User>? = ArrayList<User>()

    @Test
    fun `Given User data are available, when I search for Colton Dalton then I should receive Colton Dalton`() = runTest {
        givenUserServiceIsInitialized()
        whenUserDataAreReadAndParsed()
        thenTheUserCollectionShouldContainColtonDalton()
    }

    //Given
    private fun givenUserServiceIsInitialized(){
        userService = UserService()
    }

    //When
    private suspend fun whenUserDataAreReadAndParsed(){
        allUsers = userService.fetchUser()
    }

    //Then
    private fun thenTheUserCollectionShouldContainColtonDalton(){
        assertNotNull(allUsers)
        assertTrue(allUsers!!.isNotEmpty())
        var containsColtonDalton = false
        allUsers!!.forEach{
            if (it.fullName.equals("Colton Dalton")){
                containsColtonDalton = true
            }
        }
        assertTrue(containsColtonDalton)
    }

}
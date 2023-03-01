package com.example.workoutapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.workoutapp.dto.Groups
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class GroupTests {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var groupService : GroupService
    var allGroups : List<Groups>? = ArrayList<Groups>()

    @Test
    fun `Given group data are available when I search for Cool Group then I should receive Cool Group`() = runTest{
        givenGroupServiceIsInitialized()
        whenGroupDataAreReadAndParsed()
        thenTheGroupCollectionShouldContainCoolGroup()
    }

    //Given
    private fun givenGroupServiceIsInitialized() {
        groupService = GroupService()
    }

    //When
    private suspend fun whenGroupDataAreReadAndParsed() {
        allGroups = groupSerivce.fetchGroup()
    }

    //Then
    private fun thenTheGroupCollectionShouldContainCoolGroup() {
        assertNotNull(allGroups)
        assertTrue(allGroups!!.isNotEmpty())
        var containsCoolGroup = false
        allGroups!!.forEach{
            if (it.groupName.equals("Cool Group")){
                containsCoolGroup = true
            }
        }
        assertTrue(containsCoolGroup)
    }
}
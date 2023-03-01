package com.example.workoutapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.rules.TestRule

class UserTests {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

}
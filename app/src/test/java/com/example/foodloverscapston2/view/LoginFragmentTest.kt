package com.example.foodloverscapston2.view

import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test

class LoginFragmentTest {
    private lateinit var validation: LoginFragment

    @Before
    fun setUp() {
        validation = LoginFragment()
    }

    @Test
    fun checkEmail()
    {
        val result = validation.email("test12@gmail.com")

        assertTrue(result)
    }
}
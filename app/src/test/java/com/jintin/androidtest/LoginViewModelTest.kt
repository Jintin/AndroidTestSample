package com.jintin.androidtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<LoginRepository>(relaxed = true)
    private val viewModel = LoginViewModel(repository)
    
    @Test
    fun test_login_success() {
        coEvery { repository.login(any(), any()) }.returns(true)
        viewModel.login("abc", "123")
        assert(viewModel.liveData.value == true)
        coVerify { repository.login("abc", "123") }
    }

    @Test
    fun test_login_fail() {
        coEvery { repository.login(any(), any()) }.returns(false)
        viewModel.login("abc", "123")
        assert(viewModel.liveData.value == false)
        coVerify { repository.login("abc", "123") }
    }
}
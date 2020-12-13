package com.jintin.androidtest

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginRepositoryTest {

    private val loginApi = mockk<LoginApi>(relaxed = true)
    private val loginRepository = LoginRepository(loginApi)

    @Test
    fun login_parameter(): Unit = runBlocking {
        loginRepository.login("abc", "123")
        coVerify { loginApi.login("abc", "123") }
    }

    @Test
    fun login_success(): Unit = runBlocking {
        coEvery {
            loginApi.login(any(), any())
        }.returns(true)

        val success = loginRepository.login("abc", "123")
        assert(success)
    }

    @Test
    fun login_fail(): Unit = runBlocking {
        coEvery {
            loginApi.login(any(), any())
        }.returns(false)

        val success = loginRepository.login("abc", "123")
        assert(!success)
    }
}
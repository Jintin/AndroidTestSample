package com.jintin.androidtest

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginRepository2Test {

    private val loginApi = mockk<LoginApi>(relaxed = true)
    private val loginRepository = LoginRepository(loginApi)

    @Test
    fun login_parameter(): Unit = runBlocking {
        loginRepository.login2("abc", "123")
        verify { loginApi.login2("abc", "123") }
    }

    @Test
    fun login_success(): Unit = runBlocking {
        every {
            loginApi.login2(any(), any())
        }.returns(Single.just(true))

        loginRepository.login2("abc", "123")
            .test()
            .assertValue(true)
    }

    @Test
    fun login_fail(): Unit = runBlocking {
        every {
            loginApi.login2(any(), any())
        }.returns(Single.just(false))

        loginRepository.login2("abc", "123")
            .test()
            .assertValue(false)
    }
}
package com.jintin.androidtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModel2Test {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<LoginRepository>(relaxed = true)
    private val viewModel = LoginViewModel(repository)

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
    }

    @Test
    fun test_login2_parameter() {
        every { repository.login2(any(), any()) }.returns(Single.just(true))
        viewModel.login2("abc", "123")
        verify { repository.login2("abc", "123") }
    }

    @Test
    fun test_login2_success() {
        every { repository.login2(any(), any()) }.returns(Single.just(true))
        viewModel.login2("abc", "123")
        assert(viewModel.liveData.value == true)
    }

    @Test
    fun test_login2_fail() {
        every { repository.login2(any(), any()) }.returns(Single.just(false))
        viewModel.login2("abc", "123")
        assert(viewModel.liveData.value == false)
    }
}
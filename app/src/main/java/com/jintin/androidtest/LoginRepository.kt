package com.jintin.androidtest

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(private val loginApi: LoginApi) {

    suspend fun login(account: String, password: String): Boolean {
        return loginApi.login(account, password)
    }

    fun login2(account: String, password: String): Single<Boolean> {
        return loginApi.login2(account, password)
    }
}
package com.jintin.androidtest

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {
    @GET("login/")
    suspend fun login(
        @Query("account") account: String,
        @Query("password") password: String
    ): Boolean

    @GET("login/")
    fun login2(
        @Query("account") account: String,
        @Query("password") password: String
    ): Single<Boolean>

}
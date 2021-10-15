package com.buupass.test.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GetUserApiService {

    @GET("/api/users/{userId}")
    fun getUser(@Path("userId") userId: String): Observable<GetUserResponse>

}
package com.buupass.test.repositories

import com.buupass.test.api.GetUserApiService
import javax.inject.Inject

class UserRepository @Inject constructor(
    var apiService: GetUserApiService
) {

    fun getUser(userId: String) = apiService.getUser(userId = userId)

}
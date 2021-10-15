package com.buupass.test.api


import com.google.gson.annotations.SerializedName

data class GetUserResponse(
    @SerializedName("data")
    val userData: UserData,
    @SerializedName("support")
    val support: Support
)
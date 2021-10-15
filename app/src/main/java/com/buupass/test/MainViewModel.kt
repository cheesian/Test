package com.buupass.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val snackBarMessage = MutableLiveData<Int>()
    val toastMessage = MutableLiveData("")
    val showLoading = MutableLiveData(false)

    fun resetPassword() {
        snackBarMessage.value = R.string.reset_password_message
    }

    fun login(username: String, password: String) {
        showLoading.value = true
        toastMessage.value = "$username $password"
    }

}
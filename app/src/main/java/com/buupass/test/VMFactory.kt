package com.buupass.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.buupass.test.repositories.UserRepository
import javax.inject.Inject

class VMFactory @Inject constructor(
    val userRepository: UserRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(userRepository = userRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }

}
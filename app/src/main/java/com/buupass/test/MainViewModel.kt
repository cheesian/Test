package com.buupass.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buupass.test.repositories.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.ConnectException
import java.util.*

class MainViewModel(
    val userRepository: UserRepository
): ViewModel() {

    val snackBarMessage = MutableLiveData<Int>()
    val toastMessage = MutableLiveData("")
    val showLoading = MutableLiveData(false)
    val disposable = CompositeDisposable()
    val loginSuccessful = MutableLiveData(false)

    private fun getUser(userId: String, username: String, password: String){
        disposable.add(
            userRepository.getUser(userId = userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showLoading() }
                .doOnTerminate { stopLoading() }
                .subscribe(
                    {
                        val firstName = it.userData.firstName.lowercase(Locale.getDefault())
                        val lastName = it.userData.lastName.lowercase(Locale.getDefault())
                        val name = username.lowercase(Locale.getDefault())
                        if (!(name.contains(firstName) && name.contains(lastName))) {
                            snackBarMessage.value = R.string.login_failed
                        } else {
                            loginSuccessful.value = true
                        }
                    },
                    {
                        snackBarMessage.value = when(it) {
                            is HttpException, is ConnectException, -> R.string.network_error
                            else -> R.string.unknown_error
                        }
                    }
                )
        )
    }

    fun resetPassword() {
        snackBarMessage.value = R.string.reset_password_message
    }

    fun login(username: String, password: String) {
        getUser(userId = "2", username = username, password = password)
    }

    private fun showLoading() {
        showLoading.value = true
    }

    private fun stopLoading() {
        showLoading.value = false
    }

}
package com.jintin.androidtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    val liveData = MutableLiveData<Boolean>()

    fun login(account: String, password: String) {
        viewModelScope.launch {
            val value = repository.login(account, password)
            liveData.value = value
        }
    }

    fun login2(account: String, password: String) {
        repository.login2(account, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = it
            }, {
                liveData.value = false
            })
    }

    @Suppress("UNCHECKED_CAST")
    class LoginViewModelFactory @Inject constructor(private val repository: LoginRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(repository) as T
        }
    }
}
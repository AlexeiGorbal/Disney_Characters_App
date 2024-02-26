package com.example.apiapplication.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apiapplication.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val state = MutableLiveData<UiState>()

    fun searchUser(email: String, password: String) {

        repository.getUser(email, password, {
            state.value = UiState.UserFound
        }, {
            state.value = UiState.UserNotFound
        })
    }
}
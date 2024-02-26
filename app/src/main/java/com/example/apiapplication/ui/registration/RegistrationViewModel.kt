package com.example.apiapplication.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiapplication.repository.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val state = MutableLiveData<UiState>()

    fun saveUser(email: String, password: String) {

        if (email.isBlank()) {
            state.value = UiState.WrongEmail
        }

        if (password.isBlank()) {
            state.value = UiState.WrongPassword
        }

        if (email.isNotBlank() && password.isNotBlank()) {
            repository.addUser(email, password)
            state.value = UiState.Saved
        }
    }
}
package com.example.apiapplication.ui.registration

sealed class UiState {

    data object WrongEmail : UiState()

    data object WrongPassword : UiState()

    data object Saved : UiState()
}
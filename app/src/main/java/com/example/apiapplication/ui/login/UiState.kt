package com.example.apiapplication.ui.login

sealed class UiState {
    data object UserFound : UiState()
    data object UserNotFound : UiState()
}
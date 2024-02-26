package com.example.apiapplication.ui.character.list

import com.example.apiapplication.model.Character

sealed class CharacterListState {

    data object Loading : CharacterListState()
    data object Error : CharacterListState()
    data class Loaded(val characters: List<Character>) : CharacterListState()
    data class OpenInformationScreen(val id: String) : CharacterListState()
}
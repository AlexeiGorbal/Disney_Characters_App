package com.example.apiapplication.ui.character.list

import com.example.apiapplication.model.Character

sealed class CharacterListState {

    object Loading : CharacterListState()
    object Error : CharacterListState()
    data class Loaded(val characters: List<Character>) : CharacterListState()
    data class OpenInformationScreen(val id: String) : CharacterListState()
}
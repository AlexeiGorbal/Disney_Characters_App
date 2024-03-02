package com.example.apiapplication.ui.character.list

sealed class CharacterListAction {

    data class CharacterClick(val id: String) : CharacterListAction()
}
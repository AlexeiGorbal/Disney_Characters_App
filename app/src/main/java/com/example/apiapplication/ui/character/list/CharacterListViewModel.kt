package com.example.apiapplication.ui.character.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiapplication.model.Character
import com.example.apiapplication.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    val state = MutableLiveData<CharacterListState>()

    init {
        state.value = CharacterListState.Loading
        viewModelScope.launch {
            try {
                state.value = CharacterListState.Loaded(repository.getCharacters())
            } catch (e: Exception) {
                state.value = CharacterListState.Error
            }
        }
    }

    fun processAction(action: CharacterListAction) {
        if (action is CharacterListAction.CharacterClick) {
            state.value = CharacterListState.OpenInformationScreen(action.id)
        }
    }
}
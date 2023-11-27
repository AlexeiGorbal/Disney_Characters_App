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

    val charaters = MutableLiveData<List<Character>>()

    init {
        viewModelScope.launch {
            charaters.value = repository.getCharacters()
        }
    }
}
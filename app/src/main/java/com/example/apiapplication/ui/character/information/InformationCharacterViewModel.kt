package com.example.apiapplication.ui.character.information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiapplication.model.Character
import com.example.apiapplication.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InformationCharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    val character = MutableLiveData<Character?>()

    fun loadCharacter(id: String) {
        viewModelScope.launch {
            character.value = repository.getCharacter(id)
        }
    }
}
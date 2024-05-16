package com.example.rickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class CharacterViewModel : ViewModel() {
    private val repository = CharacterRepository()
    val characters = MutableLiveData<List<Character>>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun fetchCharacters() {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getCharacters()
                characters.postValue(response.results)
            } catch (e: IOException) {
                errorMessage.postValue("Network Error: Please check your internet connection")
            } catch (e: Exception) {
                errorMessage.postValue("Error Occurred: ${e.message}")
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}

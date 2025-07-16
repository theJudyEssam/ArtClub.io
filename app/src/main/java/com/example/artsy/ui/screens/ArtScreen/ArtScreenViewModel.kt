package com.example.artsy.ui.screens.ArtScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.artsy.ArtApplication
import com.example.artsy.data.model.ArtPiece
import com.example.artsy.data.repository.ArtRepository
import com.example.artsy.ui.screens.MainScreen.MainScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtScreenViewModel(private val repository: ArtRepository): ViewModel() {
    private var _artPiece = MutableStateFlow<ArtPiece?>(null)
    var artPiece: StateFlow<ArtPiece?> = _artPiece
    fun fetchArtPiecebyId (id: Int){
        viewModelScope.launch {
            try{
                _artPiece.value = repository.getArtworkById(id)
            }
            catch(e: Exception){
                Log.e("API ERROR", "${e.message}")
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ArtApplication)
                val artRepository = application.container.artRepository
                ArtScreenViewModel(repository = artRepository)
            }
        }
    }

}
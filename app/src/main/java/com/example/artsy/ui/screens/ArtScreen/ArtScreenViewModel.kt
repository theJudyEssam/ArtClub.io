package com.example.artsy.ui.screens.ArtScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.artsy.ArtApplication
import com.example.artsy.data.model.ArtPiece
import com.example.artsy.data.repository.DBArtRepository
import com.example.artsy.data.repository.NetworkArtRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtScreenViewModel(private val repository: NetworkArtRepository, private val DBrepository: DBArtRepository): ViewModel() {
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


    fun AddPiecetoFavourites(art: ArtPiece){
        viewModelScope.launch {
            art.let {
                DBrepository.insertItem(it)
            }

        }
    }

    fun RemovefromFavourites(art: ArtPiece){
        viewModelScope.launch {
            art.let {
                DBrepository.insertItem(it)
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ArtApplication)
                val artRepository = application.container.NetworkartRepository
                val DBArtRepository = application.container.DBArtRepository
                ArtScreenViewModel(repository = artRepository, DBrepository = DBArtRepository)
            }
        }
    }
}
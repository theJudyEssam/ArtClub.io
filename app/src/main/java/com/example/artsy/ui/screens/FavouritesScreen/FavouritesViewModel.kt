package com.example.artsy.ui.screens.FavouritesScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.artsy.ArtApplication
import com.example.artsy.data.model.ArtPiece
import com.example.artsy.data.repository.DBArtRepository
import com.example.artsy.ui.screens.MainScreen.MainArtUiState
import com.example.artsy.ui.screens.MainScreen.MainScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class FavouritesUIState(val artList: List<ArtPiece> = listOf())
class FavouritesViewModel(private val DBrepository: DBArtRepository): ViewModel() {

    val _favouritesUiState: StateFlow<FavouritesUIState> =
        DBrepository.getArtworks().map { FavouritesUIState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = FavouritesUIState()
            )

    var favouritesUIState: StateFlow<FavouritesUIState> = _favouritesUiState

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ArtApplication)
                val DBArtRepository = application.container.DBArtRepository
                FavouritesViewModel(DBArtRepository)
            }
        }
    }
}
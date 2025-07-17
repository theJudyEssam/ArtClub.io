package com.example.artsy.ui.screens.MainScreen
import android.util.Log
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
import com.example.artsy.data.repository.NetworkArtRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface MainArtUiState {
    data class Success(val photos: List<ArtPiece>) : MainArtUiState
    object Error : MainArtUiState
    object Loading : MainArtUiState
}


class MainScreenViewModel(private val ArtRepository: NetworkArtRepository) : ViewModel() {

     var artUiState: MainArtUiState by mutableStateOf(MainArtUiState.Loading)
        private set


    init{
        getArtWorks()
    }

    fun getArtWorks(){
    viewModelScope.launch {
        artUiState = MainArtUiState.Loading
        artUiState = try{
            MainArtUiState.Success(ArtRepository.getArtworks())
        } catch (e: IOException) {
            MainArtUiState.Error
        } catch (e: HttpException) {
            MainArtUiState.Error
        }

        Log.d("API check", artUiState.toString())
    }
    }


    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ArtApplication)
                val artRepository = application.container.NetworkartRepository
                MainScreenViewModel(ArtRepository = artRepository)
            }
        }
    }

}
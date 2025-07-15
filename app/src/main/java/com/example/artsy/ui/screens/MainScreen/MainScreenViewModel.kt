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
import com.example.artsy.data.repository.ArtRepository
import kotlinx.coroutines.launch
import okhttp3.Call
import retrofit2.HttpException
import java.io.IOException


sealed interface ArtUiState {
    data class Success(val photos: List<ArtPiece>) : ArtUiState
    object Error : ArtUiState
    object Loading : ArtUiState
}


class MainScreenViewModel(private val ArtRepository: ArtRepository) : ViewModel() {

     var artUiState: ArtUiState by mutableStateOf(ArtUiState.Loading)
        private set


    init{
        getArtWorks()
    }

    fun getArtWorks(){
    viewModelScope.launch {
        artUiState = ArtUiState.Loading
        artUiState = try{
            ArtUiState.Success(ArtRepository.getArtworks())
        } catch (e: IOException) {
            ArtUiState.Error
        } catch (e: HttpException) {
            ArtUiState.Error
        }

        Log.d("API check", artUiState.toString())
    }
    }



    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ArtApplication)
                val artRepository = application.container.artRepository
                MainScreenViewModel(ArtRepository = artRepository)
            }
        }
    }

}
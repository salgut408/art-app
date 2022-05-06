package com.example.myapplicationagian.main

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplicationagian.ArtworkObject
import com.example.myapplicationagian.room.getDatabase
import com.example.myapplicationagian.repository.ArtworkRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val artworkRepository = ArtworkRepository(database)
    val artworkList = artworkRepository.allArtworks

    private val _navigateToDetailArtworkObject = MutableLiveData<ArtworkObject>()
    val navigateToDetailArtworkObject: LiveData<ArtworkObject>
        get() = _navigateToDetailArtworkObject



    init {
        viewModelScope.launch {
            artworkRepository.refreshArtworks()

        }
    }
    fun onArtworkClicked(artwork: ArtworkObject) {
        _navigateToDetailArtworkObject.value = artwork
    }

    class Factory(val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to contstruct viewmodel")
        }
    }


}
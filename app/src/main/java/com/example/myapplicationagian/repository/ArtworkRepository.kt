package com.example.myapplicationagian.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.myapplicationagian.*
import com.example.myapplicationagian.room.ArtworkDatabase
import com.example.myapplicationagian.room.asDatabaseModel
import com.example.myapplicationagian.room.asDomainModel
import com.example.myapplicationagian.api.ArtApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArtworkRepository(private val database: ArtworkDatabase) {
    val allArtworks: LiveData<List<ArtworkObject>> =
        Transformations.map(database.artDao.getArt()) {
            it.asDomainModel()
        }


    suspend fun refreshArtworks() {
        withContext(Dispatchers.IO) {
            try{
                var artworks = ArtApiService.ArtApi.restrofitService.getArt(fieldTerms, searchTerm, limit)
                database.artDao.clear()
                database.artDao.insertAll(artworks.artworkObject.asDatabaseModel())
                Log.i("artworks", "refreshArtwork fail")
            } catch (err: Exception) {
                Log.i("fail", err.message.toString())

            }
        }
    }

}


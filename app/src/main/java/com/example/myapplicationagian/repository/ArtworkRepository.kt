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
        Transformations.map(database.artDao.getArtInDB()) {
            it.asDomainModel()
        }
    val alphaArtworks: LiveData<List<ArtworkObject>> =
        Transformations.map(database.artDao.getArtAplha()) {
            it.asDomainModel()
        }
    val artSortByCountry: LiveData<List<ArtworkObject>> =
        Transformations.map(database.artDao.placeOfOriginFilter()) {
            it.asDomainModel()
        }
    val mostColorfulSort: LiveData<List<ArtworkObject>> =
        Transformations.map(database.artDao.sortByMostColorfulness()) {
            it.asDomainModel()
        }
    val leastColorfulSort: LiveData<List<ArtworkObject>> =
        Transformations.map(database.artDao.sortByLeastColorfulness()) {
            it.asDomainModel()
        }


    suspend fun refreshArtworks() {
        withContext(Dispatchers.IO) {
            try{

                val artworks = ArtApiService.ArtApi.restrofitService.getArt(fieldTerms, searchTerm, limit)
                database.artDao.clear()
                database.artDao.insertAll(artworks.artworkObject.asDatabaseModel())
                database.artDao.getArtInDB()

                Log.i("artworks", "refreshArtwork Works")
            } catch (err: Exception) {
                Log.i("fail", err.message.toString())

            }
        }
    }

}


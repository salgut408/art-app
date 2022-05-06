package com.example.myapplicationagian.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplicationagian.ArtworkObject

@Entity(tableName = "artworks")
data class DatabaseArtwork constructor(
    @PrimaryKey(autoGenerate = false)
    val score       : Double,
    val artistTitle : String?,
    val imageId     : String?,
    val title       : String?
)

fun List<DatabaseArtwork>.asDomainModel(): List<ArtworkObject>{
    return map {
        ArtworkObject(
            score = it.score,
            artistTitle = it.artistTitle,
            imageId = it.imageId,
            title =  it.title
        )
    }
}

fun List<ArtworkObject>.asDatabaseModel(): List<DatabaseArtwork>{
    return map {
        DatabaseArtwork(
            score = it.score,
            artistTitle = it.artistTitle,
            imageId = it.imageId,
            title =  it.title
        )
    }
}
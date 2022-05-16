package com.example.myapplicationagian.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplicationagian.ArtworkObject

@Entity(tableName = "artworks")
data class DatabaseArtwork constructor(

    val score: Double,
    val artistTitle: String?,
    val imageId: String?,
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val place_of_origin: String?,
    val credit_line: String?,
    val colorfulness: Float?
)

fun List<DatabaseArtwork>.asDomainModel(): List<ArtworkObject> {
    return map {
        ArtworkObject(
            score = it.score,
            artistTitle = it.artistTitle,
            imageId = it.imageId,
            title = it.title,
            place_of_origin = it.place_of_origin,
            credit_line = it.credit_line,
            colorfulness = it.colorfulness
        )
    }
}

fun List<ArtworkObject>.asDatabaseModel(): List<DatabaseArtwork> {
    return map {
        DatabaseArtwork(
            score = it.score,
            artistTitle = it.artistTitle,
            imageId = it.imageId,
            title = it.title,
            place_of_origin = it.place_of_origin,
            credit_line  = it.credit_line,
            colorfulness = it.colorfulness
        )
    }
}
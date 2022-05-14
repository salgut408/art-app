package com.example.myapplicationagian.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplicationagian.placeOfOrigin

@Dao
interface ArtDao {

    @Query("SELECT * FROM artworks")
    fun getArt(): LiveData<List<DatabaseArtwork>>

    @Query("DELETE FROM artworks")
    fun clear()
    
    @Query("SELECT * FROM artworks WHERE place_of_origin = :placeOfOrigin ORDER BY title" )
            fun placeOfOriginFilter(placeOfOrigin: String): LiveData<List<DatabaseArtwork>>

    @Query("SELECT * FROM artworks ORDER BY artistTitle ASC")
    fun getArtAplha(): LiveData<List<DatabaseArtwork>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( artwork: List<DatabaseArtwork>)
//filter place of origin
    @Query("SELECT * FROM artworks ORDER BY place_of_origin")
    fun getArtFrom(): LiveData<List<DatabaseArtwork>>

}


@Database(entities = [DatabaseArtwork::class], version = 6, exportSchema = false)
abstract class ArtworkDatabase: RoomDatabase() {
    abstract val artDao: ArtDao
}

private lateinit var INSTANCE: ArtworkDatabase

fun getDatabase(context: Context): ArtworkDatabase {
    synchronized(ArtworkDatabase::class.java) {
        if(!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
            ArtworkDatabase::class.java,
            "artworks")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}
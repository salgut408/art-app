package com.example.myapplicationagian.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArtDao {

    @Query("SELECT * FROM artworks ORDER BY artistTitle")
    fun getArt(): LiveData<List<DatabaseArtwork>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg artwork: List<DatabaseArtwork>)


}


@Database(entities = [DatabaseArtwork::class], version = 1, exportSchema = false)
abstract class ArtworkDatabase: RoomDatabase() {
    abstract val artDao: ArtDao
}

private lateinit var INSTANCE: ArtworkDatabase

fun getDatabase(context: Context): ArtworkDatabase {
    synchronized(ArtworkDatabase::class.java) {
        if(!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
            ArtworkDatabase::class.java,
            "artworks").build()
        }
    }
    return INSTANCE
}
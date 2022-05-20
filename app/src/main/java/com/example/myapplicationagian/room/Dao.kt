package com.example.myapplicationagian.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplicationagian.ArtworkObject


@Dao
interface ArtDao {

    @Query("SELECT * FROM artworks")
    fun getArtInDB(): LiveData<List<DatabaseArtwork>>

//    @Query("SELECT * FROM artworks WHERE artistTitle LIKE :name)
//    fun getPicasso(): LiveData<List<DatabaseArtwork>>

    @Query("DELETE FROM artworks")
    fun clear()

//    @Delete
//    fun deleteArt(art: ArtworkObject)

    //filter place of origin
    @Query("SELECT * FROM artworks ORDER BY place_of_origin ASC" )
            fun placeOfOriginFilter(): LiveData<List<DatabaseArtwork>>

    @Query("SELECT * FROM artworks ORDER BY artistTitle ASC")
    fun getArtAplha(): LiveData<List<DatabaseArtwork>>

    @Query("SELECT * FROM artworks ORDER BY colorfulness DESC")
    fun sortByMostColorfulness(): LiveData<List<DatabaseArtwork>>

    @Query("SELECT * FROM artworks ORDER BY colorfulness ASC")
    fun sortByLeastColorfulness(): LiveData<List<DatabaseArtwork>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( artwork: List<DatabaseArtwork>)



}

// TODO Update schema
@Database(entities = [DatabaseArtwork::class], version = 12, exportSchema = false)
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
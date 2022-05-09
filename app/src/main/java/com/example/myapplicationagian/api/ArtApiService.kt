package com.example.myapplicationagian.api

import android.icu.util.TimeUnit
import com.example.myapplicationagian.ArtResult
import com.example.myapplicationagian.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.xml.datatype.DatatypeConstants.SECONDS
import kotlin.time.DurationUnit

class ArtApiService {
    interface ArtService {
        @GET("artworks/search")
        suspend fun getArt(
            @Query("fields") fieldTerms: String,
            @Query("q") searchTerm: String,
            @Query("limit") limit: String
        ) : ArtResult
    }
    object ArtApi {

//        private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
//            .readTimeout(60, TimeUnit.SECONDS)
//            .connectTimeout(60, DurationUnit.SECONDS)
//            .build()


        private val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create())
                .build()

        val restrofitService: ArtService by lazy {
            retrofit.create(ArtService::class.java)
        }
    }
}
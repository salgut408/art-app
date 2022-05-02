package com.example.myapplicationagian

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface ArtService {
    @GET("artworks/search")
   fun searchArt(
        @Query("fields") fieldTerms: String,
        @Query("q") searchTerm: String,
        @Query("limit") limit: String) : Call<ArtResult>
}
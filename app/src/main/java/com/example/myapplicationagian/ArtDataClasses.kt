package com.example.myapplicationagian

import com.google.gson.annotations.SerializedName
//Top level json response
data class ArtResult(
    @SerializedName("data"       ) var data       : List<Data> = listOf<Data>(),
//    @SerializedName("config"     ) var config     : Config?         = Config()
    )



data class Data(
    @SerializedName("_score"       ) var Score       : Double? = null,
    @SerializedName("artist_title" ) var artistTitle : String? = null,
    @SerializedName("image_id"     ) var imageId     : String? = null,
    @SerializedName("title"        ) var title       : String? = null
    ) {
        //until access to Coinfig...
        fun getArtImageUrl(): String? {
            val artUrlToDisplay = "https://www.artic.edu/iiif/2/" + imageId + "/full/843,/0/default.jpg"
            return artUrlToDisplay
        }
        fun getOtherImgUrl(): String {
            val urlForDisplay = "https://www.artic.edu/iiif/2/${imageId}/full/843,/0/default.jpg"
            return urlForDisplay
    }
}

//data class Config(
//    @SerializedName("iiif_url"    ) var iiifUrl    : String? = null,
//    @SerializedName("website_url" ) var websiteUrl : String? = null
//    )


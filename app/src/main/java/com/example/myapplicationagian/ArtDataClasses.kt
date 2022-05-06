package com.example.myapplicationagian


import com.google.gson.annotations.SerializedName
//Top level json response
data class ArtResult(
    @SerializedName("data"       ) var artworkObject       : List<ArtworkObject> = listOf<ArtworkObject>(),
    @SerializedName("config"     ) var config     : Config?         = Config()
    )



data class ArtworkObject(
    @SerializedName("_score"       ) var score       : Double,
    @SerializedName("artist_title" ) var artistTitle : String?,
    @SerializedName("image_id"     ) var imageId     : String?,
    @SerializedName("title"        ) var title       : String?,
    @SerializedName("place_of_origin") var place_of_origin : String?)
{
        //until access to Coinfig...
        fun getArtImageUrl(): String {
            val artUrlToDisplay = "https://www.artic.edu/iiif/2/" + imageId + "/full/843,/0/default.jpg"
            return artUrlToDisplay
        }
        fun getOtherImgUrl(): String {
            val urlForDisplay = "https://www.artic.edu/iiif/2/${imageId}/full/843,/0/default.jpg"
            return urlForDisplay
    }
}

data class Config(
    @SerializedName("iiif_url"    ) var iiifUrl    : String? = null,
    @SerializedName("website_url" ) var websiteUrl : String? = null
    )


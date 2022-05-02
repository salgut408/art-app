package com.example.myapplicationagian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationagian.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private  const val BASE_URL = "https://api.artic.edu/api/v1/"
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //list of art
        val artWorks = mutableListOf<Data>()
        //adapter for rec view
        val adapter = DataAdapter(this, artWorks)
    //rec view

        binding.rvArtWorks.adapter = adapter
        binding.rvArtWorks.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
       val artService = retrofit.create(ArtService::class.java)
        artService.searchArt("title,image_id,iiif_url,artist_title", "gay", "100")
                .enqueue(object : Callback<ArtResult> {
            override fun onResponse(call: Call<ArtResult>, response: Response<ArtResult>) {
                Log.i(TAG, "onResponse $response")
                val body = response.body()
                if(body == null) {
                    Log.i(TAG, "Did not get response")
                    return
                }
                artWorks.addAll(body.data)

                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArtResult>, t: Throwable) {
                Log.i(TAG, "OnFail $t")
            }

        })
    }
}











































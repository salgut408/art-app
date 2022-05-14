package com.example.myapplicationagian.artDetailScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplicationagian.R
import com.example.myapplicationagian.databinding.FragmentArtDetailBinding

class ArtDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentArtDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val artwork = ArtDetailFragmentArgs.fromBundle(requireArguments()).selectedArtwork
        binding.artworkDetail = artwork
        return binding.root
    }


}
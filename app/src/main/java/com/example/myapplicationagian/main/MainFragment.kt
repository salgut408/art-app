package com.example.myapplicationagian.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationagian.ArtworkObject
import com.example.myapplicationagian.main.MainAdapter
import com.example.myapplicationagian.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }

        ViewModelProvider(
            this,
            MainViewModel.Factory(activity.application)
        ).get(MainViewModel::class.java)
    }
     private val artAdapter = MainAdapter(MainAdapter.ArtworkListener { artwork ->
         viewModel.onArtworkClicked(artwork)
     })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        binding.rvArtWorks.adapter = artAdapter

//       setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.artworkList.observe(viewLifecycleOwner, Observer<List<ArtworkObject>>{ artwork ->
            artwork.apply {
                artAdapter.submitList(this)
            }
        })
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.main_overflow_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }




}




























































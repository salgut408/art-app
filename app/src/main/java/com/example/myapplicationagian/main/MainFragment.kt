package com.example.myapplicationagian.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.myapplicationagian.ArtworkObject
import com.example.myapplicationagian.R
import com.example.myapplicationagian.main.MainAdapter
import com.example.myapplicationagian.databinding.FragmentMainBinding
import com.example.myapplicationagian.utils.FilterArt


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

        viewModel.navigateToDetailArtworkObject.observe(viewLifecycleOwner, Observer { artwork ->
            if (artwork != null) {

                this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToArtDetailFragment(artwork))
                viewModel.onArtworkNavigated()
            }
        })

       setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_overlay, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }



}

































































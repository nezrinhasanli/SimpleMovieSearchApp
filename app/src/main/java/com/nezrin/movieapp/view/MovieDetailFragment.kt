package com.nezrin.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.nezrin.movieapp.databinding.FragmentMovieDetailBinding
import com.nezrin.movieapp.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val viewmodel by viewModels<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMovieDetailBinding.inflate(inflater,container,false)

        viewmodel.getMovieByIDVM(args.id)
        viewmodel.movieId.observe(viewLifecycleOwner){ movie ->
            binding.titleIs.text = movie.Title
            binding.countryIs.text = movie.Country
            binding.directorIs.text = movie.Director
            binding.writerIs.text = movie.Writer
            binding.yearIs.text=movie.Year
            Picasso.get().load(movie.Poster).into(binding.imageViewPoster)
        }


        return binding.root
    }

        }

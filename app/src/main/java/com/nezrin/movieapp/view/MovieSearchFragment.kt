package com.nezrin.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nezrin.movieapp.R
import com.nezrin.movieapp.adapter.RVAdapter
import com.nezrin.movieapp.databinding.FragmentMovieSearchBinding
import com.nezrin.movieapp.model.Search
import com.nezrin.movieapp.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MovieSearchFragment : Fragment() {
    private lateinit var binding:FragmentMovieSearchBinding
    private lateinit var adapter: RVAdapter
    private lateinit var viewmodel: MoviesViewModel
    private lateinit var searchList: ArrayList<Search>
    private lateinit var dataList: ArrayList<Search>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentMovieSearchBinding.inflate(inflater,container, false)
        binding.rv.layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.setHasFixedSize(true)
        searchList= ArrayList()
        dataList=ArrayList()

        adapter = RVAdapter(ArrayList())
        binding.rv.adapter = adapter
        viewmodel= ViewModelProvider(this)[MoviesViewModel::class.java]


        binding.search.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    viewmodel.getAllMoviesVM(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isEmpty()){
                    adapter.update(emptyList())
                }
                return true
            }

        })


        viewmodel.movieList.observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty()) {
            dataList.clear()
            dataList.addAll(it)
            searchList.clear()
            searchList.addAll(dataList)
                adapter.update(searchList)
            } else {
                Toast.makeText(requireContext(), "Meal does not exist", Toast.LENGTH_SHORT).show()

            }
        }

        return binding.root
    }

}
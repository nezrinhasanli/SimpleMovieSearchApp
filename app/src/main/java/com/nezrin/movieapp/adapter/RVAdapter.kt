package com.nezrin.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.nezrin.movieapp.databinding.CardViewBinding
import com.nezrin.movieapp.model.Search
import com.nezrin.movieapp.view.MovieSearchFragmentDirections
import com.squareup.picasso.Picasso

class RVAdapter(private var movieList:List<Search>): RecyclerView.Adapter<RVAdapter.CardViewDesignHolder>(){

        inner class CardViewDesignHolder(val view: CardViewBinding): RecyclerView.ViewHolder(view.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewDesignHolder {
            val layoutInflater= LayoutInflater.from(parent.context)
            val view=CardViewBinding.inflate(layoutInflater,parent,false)
            return CardViewDesignHolder(view)
        }

        override fun onBindViewHolder(holder: CardViewDesignHolder, position: Int) {
            val movie=movieList[position]
            val binding=holder.view

            binding.textViewMovieTitle.text=movie.Title
            Picasso.get().load(movie.Poster).into(binding.imageViewMovie)

            binding.movieItem.setOnClickListener{
                val directions=MovieSearchFragmentDirections.fromMovieSearchToMovieDetail(movie.imdbID)
                Navigation.findNavController(it).navigate(directions)
            }
        }

        override fun getItemCount(): Int {
            return movieList.size
        }
        fun update(newUserList: List<Search>){
            this.movieList= newUserList
            notifyDataSetChanged()
        }
    }

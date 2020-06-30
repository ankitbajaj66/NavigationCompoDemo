package com.example.navgraphnesting.flow1


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.navigationcompodemo.R
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        val movieListAdapter = MovieListRecyclerView(
            FakeMovieData.movies.toList(),
            object : MovieListRecyclerView.Interaction {
                override fun onItemSelected(position: Int, item: Movie) {
                    val directions =
                        MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment2(
                            position
                        )
                    navController.navigate(directions)
                }

            })
        val layoutManager = LinearLayoutManager(activity)

        recycler_movie_list.layoutManager = layoutManager
        recycler_movie_list.adapter = movieListAdapter
    }
}

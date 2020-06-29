package com.example.navgraphnesting.flow1


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.navigationcompodemo.R
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.layout_movie_list_item.view.*

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment : Fragment() {

    private var movieId: Int = -1
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = MovieDetailFragmentArgs.fromBundle(arguments!!).movieId

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = FakeMovieData.movies.get(movieId)

        setMovieDetails()
    }

    private fun setMovieDetails() {
        Glide.with(this@MovieDetailFragment)
            .load(movie.image)
            .into(movie_image)

        movie_title.text = movie.title
        movie_description.text = movie.description

        movie.star_actors?.let {
            for (index in 0 until it.size) {
                var appendValue: String = it[index]
                if (index < (it.size - 1)) {
                    appendValue += ", "
                }
                movie_star_actors.append(appendValue)
            }
        }

        movie.directors?.let {
            for (index in 0 until it.size) {
                var appendValue: String = it[index]
                if (index < (it.size - 1)) {
                    appendValue += ", "
                }
                movie_directiors.append(appendValue)
            }
        }


    }

}

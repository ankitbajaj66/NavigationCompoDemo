package com.example.navgraphnesting.flow1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navigationcompodemo.R
import kotlinx.android.synthetic.main.layout_movie_list_item.view.*

/**
 *Created by Ankit Bajaj on 29-06-2020.
 */
class MovieListRecyclerView(
    private val movieList: List<Movie>,
    private val interaction: Interaction? = null
) :
    RecyclerView.Adapter<MovieListRecyclerView.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        // Layout Inflator
        val layoutInflater = LayoutInflater.from(parent.context)

        // View
        val view = layoutInflater.inflate(R.layout.layout_movie_list_item, parent, false)

        // Make MovieViewHolder and return
        return MovieViewHolder(view, interaction)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movieList.get(position))
    }


    class MovieViewHolder(itemView: View, private val interaction: Interaction?) :
        RecyclerView.ViewHolder(itemView) {

        fun bindMovie(movie: Movie) {

            // Set Listener
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, movie)
            }
            // Set Title
            itemView.movie_title.text = movie.title

            // load Image
            Glide.with(itemView)
                .load(movie.image)
                .into(itemView.movie_image)

            movie.star_actors?.let {
                for (index in 0 until it.size) {
                    var appendValue: String = it[index]
                    if (index < (it.size - 1)) {
                        appendValue += ", "
                    }
                    itemView.movie_star_actors.append(appendValue)
                }
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Movie)
    }
}
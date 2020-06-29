package com.example.navgraphnesting.flow1

/**
 *Created by Ankit Bajaj on 29-06-2020.
 */
data class Movie(
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
    val directors: ArrayList<String>?,
    val star_actors: ArrayList<String>?
)
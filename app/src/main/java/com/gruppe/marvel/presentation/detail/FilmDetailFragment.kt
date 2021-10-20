package com.gruppe.marvel.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gruppe.marvel.databinding.FilmDetailFragmentBinding
import com.gruppe.marvel.presentation.FilmDetail
import com.gruppe.marvel.util.load


class FilmDetailFragment : Fragment() {

    private val args : FilmDetailFragmentArgs by navArgs()
    private lateinit var film : FilmDetail

    private var _bind : FilmDetailFragmentBinding? = null
    private val bind get() = _bind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _bind = FilmDetailFragmentBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        film = args.film

        prepareFields()
    }

    fun prepareFields(){
        with(bind){
            imgFilmDetail.load(film.img)
            //Glide.with(this@FilmDetailFragment).load(film.img).centerCrop().into(imgFilmDetail)
            tvFilmDetailYear.text = film.year
            tvFilmDetailRated.text = film.rated
            tvFilmGenre.text = film.genre.joinToString(  ";") { it }
            tvFilmDetailYRuntime.text = film.runtime
            tvFilmDirectorName.text = film.director[0]
            tvFilmDetailPlot.text = film.plot
            tvFilmNameCard.text = film.title
        }
    }

}
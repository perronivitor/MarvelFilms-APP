package com.gruppe.marvel.presentation.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gruppe.marvel.databinding.CardFilmBinding
import com.gruppe.marvel.presentation.FilmDetail

class MenuFavoriteAdapter(
    private var dataSet: List<FilmDetail>? = null,
    private val onItemClick: (film : FilmDetail) -> Unit
) : RecyclerView.Adapter<MenuFavoriteAdapter.ViewHolder>() {

    private var _bind : CardFilmBinding? = null
    private val bind get() = _bind!!

    class ViewHolder(bind : CardFilmBinding) : RecyclerView.ViewHolder(bind.root) {
        val img : ImageView = bind.imgCard
        val genre : TextView = bind.detailGenreCard.tvDetail
        val year : TextView = bind.detailYearCard.tvDetail
        val title :TextView = bind.tvFilmNameCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _bind = CardFilmBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataSet?.get(position)?.let {film->
            Glide.with(bind.root).load(film.img).centerCrop().into(holder.img)
            //Exibe apenas um genero do filme, demais estarão na view de detalhes
            holder.genre.text = film.genre[0]
            holder.year.text = film.year
            holder.title.text = film.title

            holder.itemView.setOnClickListener {
                onItemClick(film)
            }
        }
    }

    override fun getItemCount() = dataSet?.size ?: 0

    fun setData(data : List<FilmDetail>){
        dataSet = data
        notifyDataSetChanged()
    }
}
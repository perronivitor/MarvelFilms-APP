package com.gruppe.marvel.presentation.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gruppe.marvel.databinding.CardViewSearchBinding
import com.gruppe.marvel.presentation.FilmDetail
import com.gruppe.marvel.util.toArrayList
import com.squareup.picasso.Picasso
import java.util.ArrayList

class SearchAdapter(
    private var dataSet: MutableList<FilmDetail>? = null,
    private var dataSetFull: MutableList<FilmDetail>? = null,
    private val onItemClick: (film: FilmDetail) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var _bind: CardViewSearchBinding? = null
    private val bind get() = _bind!!

    class ViewHolder(bind: CardViewSearchBinding) : RecyclerView.ViewHolder(bind.root) {
        val img: ImageView = bind.imgCard
        val title: TextView = bind.tvFilmNameCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _bind = CardViewSearchBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataSet?.get(position)?.let { film ->
            Log.i("teste", "onBindViewHolder: ${film.img}")
            Picasso.get().load(film.img).into(holder.img)
            holder.title.text = film.title
            holder.itemView.setOnClickListener {
                onItemClick(film)
            }
        }
    }

    override fun getItemCount() = dataSet?.size ?: 0

    fun getFilter(): Filter? {
        return filtro
    }

    //Filtra a sequencia de caracteres enviados por parametro
    private val filtro: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<FilmDetail> = ArrayList()

            if (constraint.isEmpty()) {
                dataSetFull?.let { end ->
                    filteredList.addAll(end)
                }
            } else {
                val filterPattern = constraint.toString().lowercase().trim { it <= ' ' }
                dataSetFull?.forEach { item ->
                    if (item.title.lowercase().contains(filterPattern) || item.genre.contains(
                            filterPattern
                        )
                    ) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            dataSet?.clear()
            dataSet?.addAll(results.values as MutableList<FilmDetail>)
            notifyDataSetChanged()
        }
    }

    fun setData(data: List<FilmDetail>) {
        dataSet = data.toArrayList()
        dataSetFull = data.toArrayList()
        notifyDataSetChanged()
    }


}
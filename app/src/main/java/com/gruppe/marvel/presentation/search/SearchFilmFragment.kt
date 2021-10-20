package com.gruppe.marvel.presentation.search


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gruppe.cardapiofood.navigateWithAnimations
import com.gruppe.marvel.MainViewModel
import com.gruppe.marvel.R
import com.gruppe.marvel.databinding.SearchFilmFragmentBinding
import com.gruppe.marvel.presentation.FilmDetail

import com.gruppe.marvel.util.nonNullObserve
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFilmFragment : Fragment() {

    private val sharedVM: MainViewModel by sharedViewModel()

    private var _bind: SearchFilmFragmentBinding? = null
    private val bind get() = _bind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = SearchFilmFragmentBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        observer()
        prepareRecyclerView()
    }

    private fun prepareRecyclerView() {
        bind.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SearchAdapter {
                navToFragmentDetail(it)
            }
        }
    }

    private fun observer() {
        sharedVM.mAllFilms.nonNullObserve(viewLifecycleOwner) { films ->
            (bind.recyclerView.adapter as SearchAdapter).setData(films)
        }
    }

    private fun navToFragmentDetail(film: FilmDetail) {
        findNavController()
            .navigateWithAnimations(
                SearchFilmFragmentDirections
                    .actionSearchFilmFragmentToFilmDetailFragment(film)
            )
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_itens, menu)
        val search = menu?.findItem(R.id.menuItemSearch)
        search.isVisible = true
        val searchView = search.actionView as? SearchView
        searchView?.queryHint = "Search title or genre"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (!p0.isNullOrBlank()) {
                    searchDataBase(p0.toString())
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (!p0.isNullOrBlank()) {
                    searchDataBase(p0.toString())
                }
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun searchDataBase(filmSearch: String) {
        (bind.recyclerView.adapter as SearchAdapter).getFilter()?.filter(filmSearch)
    }

}
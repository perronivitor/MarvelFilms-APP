package com.gruppe.marvel.rotines.search


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gruppe.cardapiofood.navigateWithAnimations
import com.gruppe.marvel.R
import com.gruppe.marvel.databinding.SearchFilmFragmentBinding

class SearchFilmFragment : Fragment() {

    private val vm: SearchFilmViewModel by viewModels()

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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_itens, menu)
        val search = menu?.findItem(R.id.menuItemSearch)
        search.isVisible = true
        val searchView = search.actionView as? SearchView
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

    private fun searchDataBase(filmSearch : String) {
        Log.i("teste", "searchDataBase: $filmSearch")

    }

}
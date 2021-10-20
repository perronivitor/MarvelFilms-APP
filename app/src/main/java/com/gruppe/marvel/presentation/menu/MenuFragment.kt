package com.gruppe.marvel.presentation.menu

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gruppe.cardapiofood.navigateWithAnimations
import com.gruppe.marvel.MainViewModel
import com.gruppe.marvel.R
import com.gruppe.marvel.databinding.MenuFragmentBinding
import com.gruppe.marvel.presentation.FilmDetail
import com.gruppe.marvel.presentation.menu.adapter.MenuFavoriteAdapter
import com.gruppe.marvel.util.nonNullObserve
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MenuFragment : Fragment() {

    private val sharedVM: MainViewModel by sharedViewModel()

    private var _bind: MenuFragmentBinding? = null
    private val bind get() = _bind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = MenuFragmentBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        prepareRecyclerView()
        observer()

    }

    private fun observer() {
        sharedVM.mAllFilms.nonNullObserve(viewLifecycleOwner) { films ->

            //(bind.categoryFavority.recyclerView.adapter as MenuFavoriteAdapter).setData(films)

            (bind.categoryRecent.recyclerView.adapter as MenuFavoriteAdapter)
                .setData(films.sortedByDescending { it.year })

            (bind.categoryAllFilms.recyclerView.adapter as MenuFavoriteAdapter).setData(films)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_itens, menu)
        menu.findItem(R.id.menuNavItemSearch).isVisible = true
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuNavItemSearch -> {
                findNavController()
                    .navigateWithAnimations(R.id.action_menuFragment_to_searchFilmFragment2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun prepareRecyclerView() {
        bind.categoryFavority.tvCategory.isVisible = false
//        bind.categoryFavority.tvCategory.text = "Favorites Films"
//        bind.categoryFavority.recyclerView.apply {
//            layoutManager = LinearLayoutManager(
//                requireContext(),
//                RecyclerView.HORIZONTAL,
//                false
//            )
//
//            adapter = MenuFavoriteAdapter {
//                navToFragmentDetail(it)
//            }
//        }
        bind.categoryRecent.tvCategory.text = "Last Releases"
        bind.categoryRecent.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL,
                false
            )

            adapter = MenuFavoriteAdapter {
                navToFragmentDetail(it)
            }
        }
        bind.categoryAllFilms.tvCategory.text = "All Films"
        bind.categoryAllFilms.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL,
                false
            )

            adapter = MenuFavoriteAdapter {
                navToFragmentDetail(it)
            }
        }
    }

    private fun navToFragmentDetail(film: FilmDetail) {
        findNavController()
            .navigateWithAnimations(
                MenuFragmentDirections
                    .actionMenuFragmentToFilmDetailFragment(film)
            )
    }


}
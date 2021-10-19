package com.gruppe.marvel.rotines.menu

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gruppe.cardapiofood.navigateWithAnimations
import com.gruppe.marvel.R
import com.gruppe.marvel.databinding.MenuFragmentBinding

class MenuFragment : Fragment() {

    private val vm: MenuViewModel by viewModels()

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
        bind.categoryFavority.tvCategory.setOnClickListener {
            findNavController()
                .navigateWithAnimations(R.id.action_menuFragment_to_filmDetailFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_itens, menu)
        menu.findItem(R.id.menuNavItemSearch).isVisible = true
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuNavItemSearch ->{
                    findNavController()
                        .navigateWithAnimations(R.id.action_menuFragment_to_searchFilmFragment2)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
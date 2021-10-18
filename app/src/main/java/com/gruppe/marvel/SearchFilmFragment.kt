package com.gruppe.marvel


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gruppe.marvel.databinding.SearchFilmFragmentBinding

class SearchFilmFragment : Fragment() {

    private val vm: SearchFilmViewModel by viewModels()

    private var _bind : SearchFilmFragmentBinding? = null
    private val bind get() = _bind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = SearchFilmFragmentBinding.inflate(layoutInflater)
        return  bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         
    }

}
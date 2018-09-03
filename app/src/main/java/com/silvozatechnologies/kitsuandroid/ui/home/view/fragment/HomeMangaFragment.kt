package com.silvozatechnologies.kitsuandroid.ui.home.view.fragment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silvozatechnologies.kitsuandroid.R
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity
import com.silvozatechnologies.kitsuandroid.ui.home.view.adapter.HomeMediaAdapter
import com.silvozatechnologies.kitsuandroid.ui.home.viewmodel.HomeMangaViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeMangaFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var highestRatedMangaAdapter: HomeMediaAdapter
    private lateinit var mostPopularMangaAdapter: HomeMediaAdapter

    private lateinit var viewModel: HomeMangaViewModel

    private val listener = object : HomeMediaAdapter.Listener {
        override fun onItemClicked(animeEntity: AnimeEntity) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_manga, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeMangaViewModel::class.java)
    }
}
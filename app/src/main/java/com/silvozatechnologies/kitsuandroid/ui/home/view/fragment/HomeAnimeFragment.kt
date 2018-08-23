package com.silvozatechnologies.kitsuandroid.ui.home.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silvozatechnologies.kitsuandroid.R
import com.silvozatechnologies.kitsuandroid.ui.home.view.adapter.HomeMediaAdapter
import com.silvozatechnologies.kitsuandroid.ui.home.viewmodel.HomeAnimeViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home_anime.*
import javax.inject.Inject

class HomeAnimeFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var currentAnimeAdapter: HomeMediaAdapter

    private lateinit var viewModel: HomeAnimeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_anime, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()
        initializeLists()
        startObserving()
        viewModel.getCurrentAnime()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeAnimeViewModel::class.java)
    }

    private fun initializeLists() {
        currentAnimeAdapter = HomeMediaAdapter(this)
        recyclerview_current_anime.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerview_current_anime.adapter = currentAnimeAdapter
    }

    private fun startObserving() {
        viewModel.currentAnimeList.observe(this, Observer {
            if (it != null) {
                currentAnimeAdapter.setAnimeEntities(it)
            }
        })
    }
}
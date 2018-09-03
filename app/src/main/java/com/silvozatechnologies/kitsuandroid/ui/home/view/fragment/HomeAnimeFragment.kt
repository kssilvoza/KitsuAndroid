package com.silvozatechnologies.kitsuandroid.ui.home.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silvozatechnologies.kitsuandroid.R
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity
import com.silvozatechnologies.kitsuandroid.ui.home.view.adapter.HomeMediaAdapter
import com.silvozatechnologies.kitsuandroid.ui.home.viewmodel.HomeAnimeViewModel
import com.silvozatechnologies.kitsuandroid.ui.media.view.activity.MediaActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home_anime.*
import javax.inject.Inject

class HomeAnimeFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var currentAnimeAdapter: HomeMediaAdapter
    private lateinit var upcomingAnimeAdapter: HomeMediaAdapter

    private lateinit var viewModel: HomeAnimeViewModel

    private val listener = object : HomeMediaAdapter.Listener {
        override fun onItemClicked(animeEntity: AnimeEntity) {
            showMedia(animeEntity)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_anime, container, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()
        initializeLists()
        startObserving()
        viewModel.getCurrentAnime()
        viewModel.getUpcomingAnime()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeAnimeViewModel::class.java)
    }

    private fun initializeLists() {
        currentAnimeAdapter = HomeMediaAdapter(lifecycleOwner = this, listener = listener)
        recyclerview_current_anime.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerview_current_anime.adapter = currentAnimeAdapter

        upcomingAnimeAdapter = HomeMediaAdapter(lifecycleOwner = this, listener = listener)
        recyclerview_upcoming_anime.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerview_upcoming_anime.adapter = upcomingAnimeAdapter
    }

    private fun startObserving() {
        viewModel.currentAnimeList.observe(this, Observer { animeEntities ->
            animeEntities?.let { currentAnimeAdapter.setAnimeEntities(it) }
        })
        viewModel.upcomingAnimeList.observe(this, Observer { animeEntities ->
            animeEntities?.let { upcomingAnimeAdapter.setAnimeEntities(it) }
        })
    }

    private fun showMedia(animeEntity: AnimeEntity) {
        val intent = Intent(this@HomeAnimeFragment.activity, MediaActivity::class.java)
        startActivity(intent)
    }
}
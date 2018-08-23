package com.silvozatechnologies.kitsuandroid.ui.home.view.adapter

import android.arch.lifecycle.LifecycleOwner
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.silvozatechnologies.kitsuandroid.R
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity
import com.silvozatechnologies.kitsuandroid.databinding.ItemHomeMediaBinding
import com.silvozatechnologies.kitsuandroid.ui.home.viewmodel.HomeMediaItemViewModel
import timber.log.Timber

class HomeMediaAdapter(private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<HomeMediaAdapter.ViewHolder>() {
    private var animeEntities = listOf<AnimeEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemHomeMediaBinding>(inflater, R.layout.item_home_media, parent, false)
        binding.setLifecycleOwner(lifecycleOwner)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return animeEntities.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewmodel = HomeMediaItemViewModel(animeEntities[position])
    }

    fun setAnimeEntities(animeEntities: List<AnimeEntity>) {
        this.animeEntities = animeEntities
        Timber.d(animeEntities.toString())
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemHomeMediaBinding) : RecyclerView.ViewHolder(binding.root)
}
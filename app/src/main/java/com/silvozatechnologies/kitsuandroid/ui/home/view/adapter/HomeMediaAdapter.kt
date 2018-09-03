package com.silvozatechnologies.kitsuandroid.ui.home.view.adapter

import android.arch.lifecycle.LifecycleOwner
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.silvozatechnologies.kitsuandroid.R
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity
import com.silvozatechnologies.kitsuandroid.databinding.ItemHomeMediaBinding
import com.silvozatechnologies.kitsuandroid.ui.home.viewmodel.HomeMediaItemViewModel

class HomeMediaAdapter(
        private val lifecycleOwner: LifecycleOwner,
        private val listener: HomeMediaAdapter.Listener) : RecyclerView.Adapter<HomeMediaAdapter.ViewHolder>() {
    private var animeEntities = mutableListOf<AnimeEntity>()

    interface Listener {
        fun onItemClicked(animeEntity: AnimeEntity)
    }

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
        holder.binding.viewModel = HomeMediaItemViewModel(animeEntities[position])
        holder.binding.listener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }

        if (payloads[0] is Bundle) {
            val bundle = payloads[0] as Bundle

            if (bundle.containsKey(MediaDiffCallback.CANONICAL_TITLE_CHANGE)) {
                val canonicalTitle = bundle.getString(MediaDiffCallback.CANONICAL_TITLE_CHANGE, "")
                holder.binding.viewModel?.setCanonicalTitle(canonicalTitle)
            }
            if (bundle.containsKey(MediaDiffCallback.POSTER_IMAGE_SMALL_CHANGE)) {
                val posterImage = bundle.getString(MediaDiffCallback.POSTER_IMAGE_SMALL_CHANGE, "")
                holder.binding.viewModel?.setPosterImage(posterImage)
            }
        }
    }

    fun setAnimeEntities(animeEntities: List<AnimeEntity>) {
        val mediaDiffCallback = MediaDiffCallback(this.animeEntities, animeEntities)
        val diffResult = DiffUtil.calculateDiff(mediaDiffCallback)

        this.animeEntities.clear()
        this.animeEntities.addAll(animeEntities)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(val binding: ItemHomeMediaBinding) : RecyclerView.ViewHolder(binding.root)
}
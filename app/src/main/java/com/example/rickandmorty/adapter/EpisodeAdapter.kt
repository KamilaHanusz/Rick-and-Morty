package com.example.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemEpisodeBinding
import com.example.rickandmorty.model.Episode

class EpisodeAdapter(private val episodes: List<Episode>) :
    RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int = episodes.size

    class EpisodeViewHolder(private val binding: ItemEpisodeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Episode) {
            binding.episodeName.text = episode.name
            binding.episodeNumber.text = episode.episode
        }
    }
}
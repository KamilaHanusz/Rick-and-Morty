package com.example.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.model.Character
import com.squareup.picasso.Picasso

class CharacterAdapter(private val characters: List<Character>, private val onClick: (Character) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
        holder.itemView.setOnClickListener { onClick(character) }

        val dotColor = when (character.status) {
            "Alive" -> R.color.green
            "Dead" -> R.color.red
            else -> R.color.yellow
        }
        holder.binding.statusDot.backgroundTintList = ContextCompat.getColorStateList(holder.itemView.context, dotColor)
    }

    override fun getItemCount(): Int = characters.size

    class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.characterName.text = character.name
            binding.characterStatus.text = character.status
            Picasso.get().load(character.image).into(binding.characterImage)
        }
    }
}

package com.example.rickandmorty.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.repository.CharacterRepository
import com.example.rickandmorty.R
import com.example.rickandmorty.adapter.EpisodeAdapter
import com.example.rickandmorty.databinding.ActivityCharacterDetailBinding
import com.example.rickandmorty.model.Character
import com.squareup.picasso.Picasso
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharacterDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterDetailBinding
    private val characterRepository = CharacterRepository()
    private lateinit var episodeAdapter: EpisodeAdapter

    @OptIn(DelicateCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBackBtn.setOnClickListener {
            startActivity(Intent(this, CharacterListActivity::class.java))
            finish()
        }

        val character = intent.getParcelableExtra("character", Character::class.java)!!

        binding.characterName.text = character.name
        binding.characterStatus.text = character.status
        binding.characterSpecies.text = character.species
        binding.characterGender.text = character.gender
        binding.characterOrigin.text = character.origin.name
        binding.characterLocation.text = character.location.name
        Picasso.get().load(character.image).into(binding.characterImage)

        val dotColor = when (character.status) {
            "Alive" -> R.color.green
            "Dead" -> R.color.red
            else -> R.color.yellow
        }
        binding.statusDot.backgroundTintList = ContextCompat.getColorStateList(this, dotColor)

        showEpisodeProgressBar()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val episodes = characterRepository.getCharacterEpisodes(character.episode.map { it.substringAfterLast("/") })
                episodeAdapter = EpisodeAdapter(episodes)
                binding.episodeRecyclerView.apply {
                    layoutManager = LinearLayoutManager(this@CharacterDetailActivity)
                    adapter = episodeAdapter
                }
            } catch (e: Exception) {
                Toast.makeText(this@CharacterDetailActivity, "Error occurred while fetching episodes: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                hideEpisodeProgressBar()
            }
        }
    }

    private fun showEpisodeProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideEpisodeProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

}
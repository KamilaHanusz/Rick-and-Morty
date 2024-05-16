package com.example.rickandmorty.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.adapter.CharacterAdapter
import com.example.rickandmorty.databinding.ActivityCharacterListBinding
import com.example.rickandmorty.viewmodel.CharacterViewModel

class CharacterListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterListBinding
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            hideProgressBar()
        })

        viewModel.fetchCharacters()

        viewModel.characters.observe(this, Observer { characters ->
            binding.recyclerView.adapter = CharacterAdapter(characters) { character ->
                val intent = Intent(this, CharacterDetailActivity::class.java)
                intent.putExtra("character", character)
                startActivity(intent)
            }
            hideProgressBar()
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        })
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}

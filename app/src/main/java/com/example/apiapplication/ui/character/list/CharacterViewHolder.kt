package com.example.apiapplication.ui.character.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.api_application.R
import com.example.api_application.databinding.ItemCharacterBinding
import com.example.apiapplication.model.Character

class CharacterViewHolder(view: View) : ViewHolder(view) {

    private val binding: ItemCharacterBinding = ItemCharacterBinding.bind(view)

    fun bind(character: Character, onItemClick: (Character) -> Unit) {
        Glide.with(itemView.context).load(character.imageUrl).into(binding.image)
        binding.title.text = character.name
        itemView.setOnClickListener {
            onItemClick(character)
        }
    }

    companion object {

        fun from(parent: ViewGroup): CharacterViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_character,
                parent, false
            )
            return CharacterViewHolder(view)
        }
    }
}
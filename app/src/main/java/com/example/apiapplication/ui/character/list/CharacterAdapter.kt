package com.example.apiapplication.ui.character.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.apiapplication.model.Character

class CharacterAdapter(private val onItemClick: (Character) -> Unit) :
    Adapter<CharacterViewHolder>() {

    private val list = mutableListOf<Character>()

    fun setCharacters(list: List<Character>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        return holder.bind(list[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
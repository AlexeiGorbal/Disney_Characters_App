package com.example.apiapplication.ui.character.information

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.api_application.R
import com.example.api_application.databinding.FragmentInformationCharacterBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationCharacterFragment : Fragment() {

    private var _binding: FragmentInformationCharacterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InformationCharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInformationCharacterBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val id = arguments?.getString(KEY_ID)

        id?.also {
            viewModel.loadCharacter(it)
        }

        viewModel.character.observe(viewLifecycleOwner) {
            binding.name.text = it?.name
            Glide.with(this).load(it?.imageUrl).into(binding.image)
            it?.films?.forEach { film ->
                val chip = Chip(context)
                chip.text = film
                binding.filmsChip.addView(chip)
            }
            it?.shortFilms?.forEach { film ->
                val chip = Chip(context)
                chip.text = film
                binding.shortFilmsChip.addView(chip)
            }
            it?.tvShows?.forEach { film ->
                val chip = Chip(context)
                chip.text = film
                binding.tvShowsChip.addView(chip)
            }
            it?.videoGames?.forEach { film ->
                val chip = Chip(context)
                chip.text = film
                binding.videoGamesChip.addView(chip)
            }
            it?.parkAttractions?.forEach { film ->
                val chip = Chip(context)
                chip.text = film
                binding.parkAttractionsChip .addView(chip)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val KEY_ID = "id"

        fun newInstance(id: String) = InformationCharacterFragment().apply {
            val bundle = Bundle()
            bundle.putString(KEY_ID, id)
            arguments = bundle
        }
    }
}
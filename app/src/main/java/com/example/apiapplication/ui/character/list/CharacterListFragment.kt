package com.example.apiapplication.ui.character.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_application.R
import com.example.api_application.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recycler.layoutManager = LinearLayoutManager(context)

        val adapter = CharacterAdapter {
            viewModel.processAction(CharacterListAction.CharacterClick(it.id))
        }

        binding.recycler.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is CharacterListState.Loaded -> {
                    adapter.setCharacters(it.characters)
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, LIST_LOADED, Toast.LENGTH_SHORT).show()
                }

                is CharacterListState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is CharacterListState.OpenInformationScreen -> {
                    val args = Bundle().apply {
                        putString("id", it.id)
                    }
                    findNavController().navigate(
                        R.id.action_characterListFragment_to_informationCharacterFragment,
                        args
                    )
                }

                is CharacterListState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, LIST_IS_NOT_LOADED, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        const val LIST_LOADED = "List loaded"
        const val LIST_IS_NOT_LOADED = "List is not loaded"
    }
}
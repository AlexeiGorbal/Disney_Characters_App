package com.example.apiapplication.ui.character.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_application.R
import com.example.api_application.databinding.FragmentCharacterListBinding
import com.example.apiapplication.ui.character.information.InformationCharacterFragment
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
            parentFragmentManager.commit {
                add(R.id.fragment_container, InformationCharacterFragment.newInstance(it.id))
                addToBackStack(null)
            }
        }

        binding.recycler.adapter = adapter

        viewModel.charaters.observe(viewLifecycleOwner) {
            adapter.setCharacters(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun newInstance() = CharacterListFragment()
    }
}
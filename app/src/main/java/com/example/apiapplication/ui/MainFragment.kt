package com.example.apiapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.api_application.R
import com.example.api_application.databinding.FragmentMainBinding
import com.example.apiapplication.ui.character.list.CharacterListFragment
import dagger.hilt.android.AndroidEntryPoint

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.button.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, CharacterListFragment.newInstance())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
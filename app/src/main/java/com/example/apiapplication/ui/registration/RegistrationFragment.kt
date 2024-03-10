package com.example.apiapplication.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.api_application.R
import com.example.api_application.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null

    private val binding get() = _binding!!

    val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.button.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            viewModel.saveUser(email, password)
        }

        viewModel.state.observe(viewLifecycleOwner) {

            when (it) {
                is UiState.WrongEmail -> {
                    binding.email.error = resources.getString(R.string.empty_email)
                }

                is UiState.WrongPassword -> {
                    binding.password.error = resources.getString(R.string.empty_password)
                }

                is UiState.Saved -> {
                    findNavController().navigate(R.id.action_registrationFragment_to_characterListFragment)
                    Toast.makeText(context, R.string.saved, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
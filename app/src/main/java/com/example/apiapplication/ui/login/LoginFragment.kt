package com.example.apiapplication.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.api_application.R
import com.example.api_application.databinding.FragmentLoginBinding
import com.example.apiapplication.ui.character.list.CharacterListFragment
import com.example.apiapplication.ui.registration.RegistrationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.button.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            viewModel.searchUser(email, password)
        }

        viewModel.state.observe(viewLifecycleOwner) {

            when (it) {
                is UiState.UserNotFound -> {
                    Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
                }

                is UiState.UserFound -> {
                    parentFragmentManager.commit {
                        replace(R.id.fragment_container, CharacterListFragment.newInstance())
                    }
                }
            }
        }

        binding.singUp.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, RegistrationFragment.newInstance())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}
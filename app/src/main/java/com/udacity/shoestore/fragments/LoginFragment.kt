package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeStoreViewModel
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: ShoeStoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, null, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeStoreViewModel::class.java)

        setUpBinding(binding)

        return binding.root
    }

    private fun setUpBinding(binding: FragmentLoginBinding) {
        binding.viewModel = viewModel
        binding.navDirection = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
    }
}
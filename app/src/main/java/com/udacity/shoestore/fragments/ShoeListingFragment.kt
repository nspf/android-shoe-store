package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.BR
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeStoreViewModel
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.models.Shoe


class ShoeListingFragment: Fragment() {

    private lateinit var binding: FragmentShoeListingBinding
    private lateinit var viewModel: ShoeStoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_listing, null, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeStoreViewModel::class.java)

        setUpBinding(binding)
        setUpUi()

        return binding.root
    }

    private fun setUpBinding(binding: FragmentShoeListingBinding) {
        binding.viewModel = viewModel
        binding.navDirection = ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment()
    }

    private fun setUpUi() {
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            for (shoe in it) {
                addShoe(shoe)
            }
        })
    }

    private fun addShoe(shoe: Shoe) {
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.shoe_item, binding.shoesContainer, true
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.shoe, shoe)
    }
}
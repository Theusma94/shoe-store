package com.udacity.shoestore.ui.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.OnRebindCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.ui.ShoeViewModel


class ShoeDetailFragment: Fragment() {

    val shoeViewModel: ShoeViewModel by activityViewModels()

    lateinit var shoeDetailBinding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shoeDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        shoeDetailBinding.viewModel = shoeViewModel
        shoeViewModel.shoeInsertionFinalized.observe(viewLifecycleOwner, Observer {
            if(it) {
                findNavController().navigateUp()
            }
        })
        return shoeDetailBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        shoeViewModel.onDetailFinished()
    }
}
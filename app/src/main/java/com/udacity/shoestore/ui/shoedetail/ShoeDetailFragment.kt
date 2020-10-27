package com.udacity.shoestore.ui.shoedetail

import android.app.AlertDialog
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


class ShoeDetailFragment : Fragment() {

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
            if (it) {
                findNavController().navigateUp()
            }
        })
        shoeViewModel.hasMissedFields.observe(viewLifecycleOwner, Observer { isShowError ->
            if (isShowError) {
                showMissFieldError()
            }
        })
        return shoeDetailBinding.root
    }

    private fun showMissFieldError() {
        AlertDialog.Builder(requireContext())
                .setTitle(requireContext().getString(R.string.shoe_detail_dialog_error_title))
                .setMessage(requireContext().getString(R.string.shoe_detail_dialog_error_message))
                .setPositiveButton(requireContext().getString(android.R.string.ok)) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .setCancelable(false)
                .create()
                .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        shoeViewModel.onDetailFinished()
    }
}
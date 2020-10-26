package com.udacity.shoestore.ui.shoelist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.ui.ShoeViewModel
import timber.log.Timber

class ShoeListFragment: Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()

    lateinit var shoeListBinding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shoeListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        shoeListBinding.floatAddShoeButton.setOnClickListener {
            shoeViewModel.prepareInsertion()
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        shoeViewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            if(shoeList.isNotEmpty()) shoeListBinding.emptyListText.visibility = View.GONE
            shoeList.forEach { shoe ->
                val itemBinding: ItemShoeBinding = DataBindingUtil.inflate(inflater, R.layout.item_shoe, container, false)
                itemBinding.shoe = shoe
                shoeListBinding.shoeList.addView(itemBinding.root)
            }
        })
        setHasOptionsMenu(true)
        return shoeListBinding.root
    }

    private fun makeLogout() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Only one item. Not necessary check id
        makeLogout()
        return super.onOptionsItemSelected(item)
    }
}
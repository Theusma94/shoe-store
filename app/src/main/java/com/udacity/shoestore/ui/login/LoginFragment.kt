package com.udacity.shoestore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        return fragmentLoginBinding.root
    }
}
package com.udacity.shoestore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var loginBinding: FragmentLoginBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        loginBinding.loginButton.setOnClickListener {
            if (!hasMissingFields()) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }
        loginBinding.createUserButton.setOnClickListener {
            if (!hasMissingFields()) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }
        return loginBinding.root
    }

    private fun hasMissingFields(): Boolean {
        var returnCheckFields = false
        loginBinding.emailInput.apply {
            if (this.text.isEmpty()) {
                this.error = requireContext().getString(R.string.field_required)
                returnCheckFields = true
            }
        }
        loginBinding.passwordInput.apply {
            if (this.text.isEmpty()) {
                this.error = requireContext().getString(R.string.field_required)
                returnCheckFields = true
            }
        }
        return returnCheckFields
    }
}
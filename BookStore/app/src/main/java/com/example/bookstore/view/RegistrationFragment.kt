package com.example.bookstore.view

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentRegistrationBinding
import com.example.bookstore.model.User
import com.example.bookstore.model.UserService
import com.example.bookstore.viewModel.RegisterViewModel

class RegistrationFragment : Fragment() {
    lateinit var binding : FragmentRegistrationBinding
    lateinit var registerViewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)
        registerViewModel = RegisterViewModel(UserService())

        binding.submitButtonRegistration.setOnClickListener{
            registration()
        }
    }

    private fun registration() {
        var userName = binding.nameInRegistration.text.toString().trim()
        var userEmail = binding.emailInRegistration.text.toString().trim()
        var userPassword  = binding.passwordInRegistration.text.toString().trim()
        var user = User(userName,userEmail,userPassword)
        if (userName.isEmpty()) {
            binding.nameInRegistration.error = "Please enter the User Name"
            binding.nameInRegistration .requestFocus()
            return
        }
        if (userEmail.isEmpty()) {
            binding.emailInRegistration.error = "Please enter the Email Address"
            binding.emailInRegistration .requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail.toString())
                .matches()
        ) {
            binding.emailInRegistration.error = "Please enter valid Email Address"
            binding.emailInRegistration.requestFocus()
            return
        }

        if (userPassword.isEmpty()) {
            binding.passwordInRegistration.error = "Please enter the password"
            binding.passwordInRegistration.requestFocus()
            return
        }
        if (userPassword.isEmpty() || binding.passwordInRegistration .text == binding.confirmPwdInRegistration .text ) {
            binding.confirmPwdInRegistration .error = "Please cofirm the password"
            binding.confirmPwdInRegistration .requestFocus()
            return
        }
        registerViewModel.putUser(user)
        registerViewModel.registerUser.observe(viewLifecycleOwner, Observer {
            if(it.status){
                Toast.makeText(context,it.msg, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,it.msg, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
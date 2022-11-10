package com.example.bookstore.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.bookstore.R
import com.example.bookstore.databinding.ActivityMainBinding
import com.example.bookstore.databinding.FragmentLoginBinding
import com.example.bookstore.model.User
import com.example.bookstore.model.UserService
import com.example.bookstore.viewModel.LogInViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    lateinit  var binding : FragmentLoginBinding
    lateinit var auth: FirebaseAuth
    lateinit var logInViewModel: LogInViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        auth = FirebaseAuth.getInstance()
        logInViewModel = LogInViewModel(UserService())

        binding.login.setOnClickListener{
            loginCredential()
        }

        binding.notHaveAccount.setOnClickListener{
            var fragment = RegistrationFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragment_activity_container,fragment)?.commit()
        }

        binding.forgotPassword.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.forgot_password,null)
            builder.setView(view)
            val userEmail : EditText= view.findViewById<EditText>(R.id.reset_password)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener{ _, _ ->
                forgotPassword(userEmail)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener{ _, _ ->})
            builder.show()
        }
    }

    private fun loginCredential() {
        var email = binding.emailId.text.toString().trim()
        var password = binding.password.text.toString().trim()
        var name = ""

        val user = User(userId = "", userName = "",email,password)
        if (email.isEmpty()) {
            binding.emailId.error = "Please enter the Email Address"
            binding.emailId.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) {
            binding.emailId.error = "Please enter valid Email Address"
            binding.emailId.requestFocus()
            return
        }
        if (password.isEmpty()) {
            binding.password.error = "Please enter the password"
            binding.password.requestFocus()
            return
        }
        logInViewModel.loginUser(user)
        logInViewModel.userLogin.observe(viewLifecycleOwner, Observer {
            if(it.status){
                Toast.makeText(context,it.msg, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Login Fail",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun forgotPassword(userEmail: EditText) {
        var userName = ""
        var userPassword = ""
        val user = User(userId = "", userName = userName, userEmail = userEmail.text.toString(), userPassword =  userPassword)
        if (userEmail.text.toString().isEmpty()) {
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail.text.toString())
                .matches()
        ) {
            return
        }
        logInViewModel.forgotPassword(user)
        logInViewModel.forgotPwd.observe(viewLifecycleOwner, Observer {
            if(it.status){
                Toast.makeText(context,it.msg,Toast.LENGTH_SHORT).show()
            }
        })
    }
}
package com.example.bookstore.model

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class UserService {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseFireStore: FirebaseFirestore
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    val userMapStore = HashMap<String, String>()

    init {
        initService()
    }

    private fun initService() {
        auth = FirebaseAuth.getInstance()
        firebaseFireStore = FirebaseFirestore.getInstance()
        firebaseStore = FirebaseStorage.getInstance()
    }

    fun userRegistration(user: User, listener: (UserListener) -> Unit) {
        auth!!.createUserWithEmailAndPassword(
            user.userEmail, user.userPassword
        ).addOnCompleteListener() {
            if (it.isSuccessful) {
                Log.d("user Registration", "Create user successfully")
                listener(UserListener(true, "user registration successful"))
                saveFireStore(user)
            } else {
                Log.d("user Registration", "Create user fail")
                listener(UserListener(false, "user registration Failed"))
            }
        }
    }

    fun userLogin(user: User, listener: (UserListener) -> Unit) {
        auth!!.signInWithEmailAndPassword(user.userEmail, user.userPassword).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userLogin = auth.currentUser
                updateUI(userLogin)
                Log.d("Login User", "User Login successfully")
                listener(UserListener(true, "Login Successful"))
            } else {
                Log.d(ContentValues.TAG, "Authentication : Fail", task.exception)
                listener(UserListener(false, "Login Failed"))
                updateUI(null)
            }

        }
    }

    fun updateUI(currentUser: FirebaseUser?): Boolean {
        var emailToverify: Boolean = false
        if (currentUser != null) {
            emailToverify = true
            return emailToverify
        }
        return emailToverify
    }


    fun checkingForUserStatus(listener: (UserListener) -> Unit) {
        var currentUser: FirebaseUser? = auth.currentUser
        if (currentUser != null) {
            listener(UserListener(true, ""))
        }
    }

    fun userForgotPassword(user: User, listener: (UserListener) -> Unit) {
        auth!!.sendPasswordResetEmail(user.userEmail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    listener(UserListener(true, "Email is sent"))
                    Log.d(ContentValues.TAG, "Email sent")
                } else {
                    listener(UserListener(false, "Email is not sent"))
                }
            }
    }

    fun saveFireStore(user: User) {
        val userID = auth.currentUser?.uid.toString()
        //val userMapStore = HashMap<String, String>()
        userMapStore["UserId"] = userID
        userMapStore["UserName"] = user.userName
        userMapStore["UserEmail"] = user.userEmail
        userMapStore["Password"] = user.userPassword

        firebaseFireStore.collection("users").document(userID)
            .set(userMapStore).addOnSuccessListener {
            }
    }

    fun readFireStore(user: User,authListener: (AuthListener) -> Unit) {
        val userID = auth.currentUser?.uid
        lateinit var userInformation: User
        if (userID != null) {
            firebaseFireStore.collection("users").document(userID)
                .get()
                .addOnCompleteListener(OnCompleteListener {
                    if (it.isSuccessful) {
                        userInformation = User(
                            userId = it.result.getString("UserId").toString(),
                            userName = it.result.getString("UserName").toString(),
                            userEmail =
                            it.result.getString("UserEmail").toString(),
                            userPassword =
                            it.result.getString("Password").toString()
                        )
                        Log.d(
                            "UserAuthServices",
                            "Image View Created ReadFireStore ${
                                it.result.getString("Profile").toString()
                            }"
                        )
                        authListener(
                            AuthListener(
                                userInformation,
                                "Displaying the information",
                                true
                            )
                        )
                    }
                })
        };
    }
}
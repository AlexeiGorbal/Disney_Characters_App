package com.example.apiapplication.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor() {

    fun addUser(email: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
    }

    fun getUser(
        email: String,
        password: String,
        userFound: () -> Unit,
        userNotFound: () -> Unit
    ) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                userFound()
            }.addOnFailureListener {
                userNotFound()
            }
    }
}
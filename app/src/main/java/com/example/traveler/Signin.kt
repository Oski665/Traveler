package com.example.traveler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class Signin : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        databaseHelper = DatabaseHelper(this)
        val btnSignIn = findViewById<MaterialButton>(R.id.btnSignIn)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        btnSignIn.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            signIn(username, password)
        }
    }

    fun signIn(username: String, password: String) {
        if (databaseHelper.checkUser(username, password)) {
            // Dane użytkownika są poprawne, zaloguj użytkownika do aplikacji
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        } else {
            // Dane użytkownika są niepoprawne, wyświetl odpowiedni komunikat
            Toast.makeText(this, "Niepoprawna nazwa użytkownika lub hasło", Toast.LENGTH_SHORT).show()
        }
    }
}
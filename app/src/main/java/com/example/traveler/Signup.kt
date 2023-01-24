package com.example.traveler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class Signup : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        databaseHelper = DatabaseHelper(this)
        val btnSignUp = findViewById<MaterialButton>(R.id.btnSignUp)
        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        btnSignUp.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val usernameRegex = Regex(pattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")
            val passwordRegex = Regex(pattern = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+-=])[A-Za-z\\d!@#$%^&*()_+-=]{8,}$")

            if (!usernameRegex.matches(etEmail.text)) {
                Toast.makeText(this, "Błędnie wprowadzony email", Toast.LENGTH_SHORT).show()
            }

            if (!passwordRegex.matches(etPassword.text)) {
                Toast.makeText(this, "Błędnie wprowadzone hasło", Toast.LENGTH_SHORT).show()
            }
            if (databaseHelper.addUser(name, email, password)) {
                Toast.makeText(this, "Rejestracja przebiegła pomyślnie", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Wystąpił błąd podczas rejestracji", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

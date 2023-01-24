package com.example.traveler

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Home : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.readableDatabase

// Pobierz nazwę użytkownika z kolumny "username" w tabeli "users"
        val cursor = db.rawQuery("SELECT nazwa_uzytkownika FROM uzytkownicy", null)

        if (cursor.moveToFirst()) {
            // Pobierz nazwę użytkownika z kolumny "username"
            val username = cursor.getString(cursor.getColumnIndex("nazwa_uzytkownika"))
            val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
            // Ustaw tekst obiektu TextView na nazwę użytkownika
            tvWelcome.text = "Hi " + username + "!"
        }

        cursor.close()
        db.close()
    }
}
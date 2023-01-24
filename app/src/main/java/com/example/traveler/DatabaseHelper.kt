package com.example.traveler

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Nazwa bazy danych
        private const val DATABASE_NAME = "userbase.db"
        // Wersja bazy danych
        private const val DATABASE_VERSION = 1
        // Nazwa tabeli z użytkownikami
        private const val TABLE_USERS = "uzytkownicy"
        // Nazwa kolumny z identyfikatorem użytkownika
        private const val COLUMN_ID = "_id"
        // Nazwa kolumny z nazwą użytkownika
        private const val COLUMN_NAME = "nazwa_uzytkownika"
        // Nazwa kolumny z nazwą użytkownika
        private const val COLUMN_EMAIL = "email_uzytkownika"
        // Nazwa kolumny z hasłem użytkownika
        private const val COLUMN_PASSWORD = "haslo_uzytkownika"
    }

    // Metoda wywoływana podczas tworzenia bazy danych
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_USERS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COLUMN_NAME TEXT, $COLUMN_EMAIL TEXT, $COLUMN_PASSWORD TEXT)"
        db.execSQL(createTable)
    }

    // Metoda wywoływana podczas aktualizacji bazy danych
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Tutaj możesz zaktualizować strukturę bazy danych
    }

    // Metoda dodająca nowego użytkownika do bazy danych
    fun addUser(name: String,email: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }
        val success = db.insert(TABLE_USERS, null, values)
        db.close()
        return success != -1L
    }
    fun checkUser(username: String, password: String): Boolean {
        val db = writableDatabase
        val projection = arrayOf(COLUMN_ID)
        val selection = "$COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_USERS, projection, selection, selectionArgs, null, null, null)
        val count = cursor.count
        cursor.close()
        db.close()
        return count > 0
    }
}
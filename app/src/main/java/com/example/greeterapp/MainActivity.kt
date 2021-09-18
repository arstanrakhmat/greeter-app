package com.example.greeterapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var editTextName : EditText
    lateinit var textViewMessage : TextView
    lateinit var buttonGreet: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.edit_text_name)
        textViewMessage = findViewById(R.id.text_view_message)
        buttonGreet = findViewById(R.id.button_greet)

        buttonGreet.setOnClickListener {
            greet(it)
        }

    }

    fun greet(view: View) {
        if (editTextName.length() == 0) {

            textViewMessage.text = ""
            Toast.makeText(this, "Please, enter the name", Toast.LENGTH_SHORT).show()

        } else {

            textViewMessage.text = "Hello, ${editTextName.text}"
        }

        /**
         * This is for hiding the keyboard after input will be finished
         */
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * These two functions are used specially for keeping data while phone rotating
     */

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("message", "This is a saved message")
        outState.putString("name", textViewMessage.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Toast.makeText(applicationContext, "onRestoreInstanceState", Toast.LENGTH_SHORT).show()
        textViewMessage.text = savedInstanceState.getString("name")
    }
}
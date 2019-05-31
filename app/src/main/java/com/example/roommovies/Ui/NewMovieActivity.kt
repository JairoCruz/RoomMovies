package com.example.roommovies.Ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.roommovies.R

class NewMovieActivity : AppCompatActivity() {

    private lateinit var editMovieView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_movie)

        editMovieView = findViewById(R.id.edit_movie)

        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener{
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editMovieView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                val movie = editMovieView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, movie)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "REPLY"
    }
}

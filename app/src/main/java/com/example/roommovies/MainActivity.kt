package com.example.roommovies

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roommovies.Ui.MovieListAdapter
import com.example.roommovies.ViewModel.ViewModelMovie

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // TODO: 2 - CREATE A MEMBER VARIABLE FOR THE ViewModelMovie
    private lateinit var movieViewModel: ViewModelMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // TODO: 3 - USE ViewModelProviders TO ASSOCIATE MY ViewModelMovie WITH MY Activity
        /**
         *  When my Activity first starts, the ViewModelProviders will create the ViewModel.
         *  When the activity is destroyed, for example through a configuration change, the ViewModel persists.
         *  When the activity is re-created, the ViewModelProviders return the existing ViewModel.
         *
         * In onCreate().get() a ViewModel from the ViewModelProvider
         * */
        movieViewModel = ViewModelProviders.of(this).get(ViewModelMovie::class.java)

        // TODO: 1 - ADD RecyclerView IN THE create() METHOD OF MainActivity
        // Add RecyclerView
        // Find RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycleviewMovie)
        // create a val adapter and pass the context like parameter to the adapter MovieListAdapter
        val adapter = MovieListAdapter(this)
        // Add the adapter to the recyclerView
        recyclerView.adapter = adapter
        // Set LayoutManager to recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)


        // TODO: 4 - ADD AN observer FOR THE LiveData RETURNED BY getAllMovies()
        movieViewModel.allMovies.observe(this, Observer { movies ->

            // Update the cached copy of the movies in the adapter
            movies?.let { adapter.setMovies(it) } })


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

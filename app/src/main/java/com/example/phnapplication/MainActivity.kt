package com.example.phnapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = MainViewModel()


        findViewById<Button>(R.id.getUserDetailsBtn).setOnClickListener {
            viewModel.getAllUsers()
        }

        viewModel.users.observe(this, Observer {

            viewModel.getAllPosts()
        })

        viewModel.posts.observe(this, Observer {
            val id = findViewById<TextInputEditText>(R.id.userId).text.toString().trim().toInt()
            val user = viewModel._users.value?.firstOrNull { _data -> _data.id == id }
            if(user != null){
                val data = viewModel.filteredData(user)

                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)
            }
        })
    }
}
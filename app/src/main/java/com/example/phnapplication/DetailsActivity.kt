package com.example.phnapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.phnapplication.models.Data

class DetailsActivity: AppCompatActivity() {
    var data: ArrayList<Data>? = null

    private var detailsAdapter: DetailsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        data = intent.getSerializableExtra("data") as ArrayList<Data>

        detailsAdapter = DetailsAdapter()
        findViewById<RecyclerView>(R.id.recyclerView).adapter = detailsAdapter
        detailsAdapter?.setData(data)

    }
}
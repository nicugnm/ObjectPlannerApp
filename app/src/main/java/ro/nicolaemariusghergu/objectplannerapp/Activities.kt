package ro.nicolaemariusghergu.objectplannerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import ro.nicolaemariusghergu.objectplannerapp.adapters.ActivityAdapter
import ro.nicolaemariusghergu.objectplannerapp.model.Activity

import ro.nicolaemariusghergu.objectplannerapp.repository.ActivityRepository

class Activities : AppCompatActivity() {

    private lateinit var activityRepository: ActivityRepository
    private lateinit var activityAdapter: ActivityAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activities)

        val firestore = FirebaseFirestore.getInstance()

        activityRepository = ActivityRepository(firestore)

        // Initialize the adapter with an empty list

        activityAdapter = ActivityAdapter(emptyList())

        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = activityAdapter

        searchButton.setOnClickListener {
            val searchQuery = searchEditText.text.toString()
            searchActivities(searchQuery)
        }

        activityRepository.getAllActivities().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val querySnapshot = task.result
                val activities = querySnapshot.toObjects(Activity::class.java)
                activityAdapter.activities = activities
                activityAdapter.notifyDataSetChanged()
            } else {
                // Handle the error
            }
        }
    }

    private fun searchActivities(query: String) {
        activityRepository.searchActivities(query).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val querySnapshot = task.result
                val activities = querySnapshot.toObjects(Activity::class.java)
                activityAdapter.activities = activities
                activityAdapter.notifyDataSetChanged()
            } else {
                // Handle the error
            }
        }
    }
}

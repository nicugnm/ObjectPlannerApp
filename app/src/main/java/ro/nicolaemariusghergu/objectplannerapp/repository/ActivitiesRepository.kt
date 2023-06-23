package ro.nicolaemariusghergu.objectplannerapp.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import ro.nicolaemariusghergu.objectplannerapp.model.Activity

class ActivityRepository(private val firestore: FirebaseFirestore) {

    fun getAllActivities(): Task<QuerySnapshot> {
        return firestore.collection("activities").get()
    }

    fun searchActivities(query: String): Task<QuerySnapshot> {
        return firestore.collection("activities")
            .whereEqualTo("title", query)
            .get()
    }
}
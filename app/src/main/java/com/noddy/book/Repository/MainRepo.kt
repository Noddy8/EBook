package com.noddy.ebook.Repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.noddy.ebook.Models.HomeModel
import com.noddy.ebook.Utils.MyResponses

class MainRepo(val context: Context) {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseRef = firebaseDatabase.getReference("AppData").child("Home")
    private val homeLD = MutableLiveData<MyResponses<ArrayList<HomeModel>>>()

    val homeLiveData get() = homeLD

    suspend fun getHomeData() {
        homeLiveData.postValue(MyResponses.Loading())
        val TAG = "MainActivity"
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshots: DataSnapshot) {
                Log.i(TAG, "onDataChange: Value Changed")
                if (!snapshots.exists()) {
                    homeLiveData.postValue(MyResponses.Error("Data snapshot not exists"))
                    return
                }
                val tempList = ArrayList<HomeModel>()
                for (snapshot in snapshots.children) {
                    val homeModel = snapshot.getValue(HomeModel::class.java)
                    homeModel?.let {
                        tempList.add(homeModel)
                    }
                }
                if (tempList.size > 0)
                    homeLiveData.postValue(MyResponses.Success(tempList))


            }

            override fun onCancelled(error: DatabaseError) {
                homeLiveData.postValue(MyResponses.Error("Something Went Wrong with Database."))
            }

        })

    }

}
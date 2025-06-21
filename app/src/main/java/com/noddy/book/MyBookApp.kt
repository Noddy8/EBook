package com.noddy.book

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.firebase.database.FirebaseDatabase
import com.noddy.ebook.Utils.loadAdUnits
import com.noddy.ebook.Utils.loadInterstitialAdIfNull

class MyBookApp() : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {
            loadAdUnits {
                loadInterstitialAdIfNull(this)
            }
        }
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}
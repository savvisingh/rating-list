package com.example.getyourguide.ui

import android.os.Bundle
import com.example.getyourguide.R
import com.example.getyourguide.ui.userrating.RatingFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RatingFragment.newInstance())
                .commitNow()
        }
    }
}
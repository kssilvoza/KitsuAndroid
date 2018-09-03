package com.silvozatechnologies.kitsuandroid.ui.home.view.activity

import android.os.Bundle
import com.silvozatechnologies.kitsuandroid.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    private fun initializeNavigationView() {

    }
}
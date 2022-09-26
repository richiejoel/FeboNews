package com.richiejoel.febonews.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.richiejoel.febonews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Hide action bar
        supportActionBar?.hide()
    }
}
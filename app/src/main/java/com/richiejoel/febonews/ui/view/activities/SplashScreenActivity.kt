package com.richiejoel.febonews.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.richiejoel.febonews.databinding.ActivitySplashScreenBinding
import com.richiejoel.febonews.utils.UtilsView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    //5ce08b8488c340488a52b921114878df

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hide action bar
        supportActionBar?.hide()

        coroutineScope.launch {
            delay(3_000)
            UtilsView.goToMainActivity(this@SplashScreenActivity)
        }
    }
}
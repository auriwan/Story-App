package com.elthobhy.storyapp.ui.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.elthobhy.storyapp.R
import com.elthobhy.storyapp.core.utils.UserPreferences
import com.elthobhy.storyapp.databinding.ActivitySplashBinding
import com.elthobhy.storyapp.ui.auth.AuthViewModel
import com.elthobhy.storyapp.ui.auth.LoginActivity
import com.elthobhy.storyapp.ui.main.MainActivity
import org.koin.android.ext.android.inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_key")
    private lateinit var binding: ActivitySplashBinding
    private var mShouldFinish = false
    private val authViewModel by inject<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper())
            .postDelayed({
                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        Pair(binding.storyText, "storyText"),
                        Pair(binding.appText,"appText")
                    )
                authViewModel.getToken().observe(this){
                    if(it.isNullOrEmpty()){
                        startActivity(Intent(this, LoginActivity::class.java), optionsCompat.toBundle())
                    }else{
                        startActivity(Intent(this,MainActivity::class.java))
                    }
                }
                mShouldFinish = true
                finishAffinity()
            },2000)
    }

    override fun onStop() {
        super.onStop()
        if(mShouldFinish)
            finish()
    }

}
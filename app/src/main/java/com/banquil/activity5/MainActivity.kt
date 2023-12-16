package com.banquil.activity5

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.content.Intent
import com.banquil.activity5.databinding.ActivityMainBinding

    class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextScreenButton.setOnClickListener{
            val intent = Intent(this, ManagePermsActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.mymidtermproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymidtermproject.databinding.ActivityMainBinding
import com.example.mymidtermproject.databinding.ActivityPvpModeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pvpModeButton.setOnClickListener {
            val intent = Intent(this, PvpMode::class.java)
            startActivity(intent)
        }

        binding.pvcModeButton.setOnClickListener {
            val intent = Intent(this, PvcMode::class.java)
            startActivity(intent)
        }
    }
}
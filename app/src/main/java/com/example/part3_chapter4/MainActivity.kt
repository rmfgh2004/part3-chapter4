package com.example.part3_chapter4

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.part3_chapter4.databinding.ActivityMainBinding
import com.example.part3_chapter4.mvc.MvcActivity
import com.example.part3_chapter4.mvp.MvpActivity
import com.example.part3_chapter4.mvvm.MvvmActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun openMVC() {
        startActivity(Intent(this, MvcActivity::class.java))
    }

    fun openMVP() {
        startActivity(Intent(this, MvpActivity::class.java))
    }

    fun openMVVM() {
        startActivity(Intent(this, MvvmActivity::class.java))
    }

    fun openMVI() {

    }
}
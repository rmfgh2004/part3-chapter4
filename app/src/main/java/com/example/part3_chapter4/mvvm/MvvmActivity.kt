package com.example.part3_chapter4.mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.part3_chapter4.R
import com.example.part3_chapter4.databinding.ActivityMvvmBinding
import com.example.part3_chapter4.mvvm.repository.ImageRepository
import com.example.part3_chapter4.mvvm.repository.ImageRepositoryImpl

class MvvmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvvmBinding
    private val viewModel: MvvmViewModel by viewModels {
        MvvmViewModel.MvvmViewModelFactory(ImageRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMvvmBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.lifecycleOwner = this
            it.view = this
            it.viewModel = viewModel
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun loadImage() {
        viewModel.loadRandomImage()
    }
}
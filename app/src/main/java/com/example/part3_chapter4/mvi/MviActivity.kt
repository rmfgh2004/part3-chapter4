package com.example.part3_chapter4.mvi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.part3_chapter4.R
import com.example.part3_chapter4.databinding.ActivityMviBinding
import com.example.part3_chapter4.mvi.repository.ImageRepositoryImpl
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MviActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMviBinding
    private val viewModel: MviViewModel by viewModels {
        MviViewModel.MviViewModelFactory(ImageRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMviBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        observeViewModel()
    }

    fun loadImage() {
        lifecycleScope.launch {
            viewModel.channel.send(MviIntent.LoadImage)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {
                    is MviState.Idle -> {
                        binding.progressView.isVisible = false
                    }

                    is MviState.Loading -> {
                        binding.progressView.isVisible = true
                    }

                    is MviState.LoadedImage -> {
                        binding.progressView.isVisible = false
                        binding.imageView.run {
                            setBackgroundColor(state.image.color.toColorInt())
                            load(state.image.url) {
                                crossfade(300)
                            }
                        }
                        binding.imageCountTextView.text = "불러온 이미지 수 : ${state.count}"
                    }
                }
            }
        }
    }
}
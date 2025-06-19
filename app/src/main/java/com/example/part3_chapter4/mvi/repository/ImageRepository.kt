package com.example.part3_chapter4.mvi.repository

import com.example.part3_chapter4.mvi.model.Image

interface ImageRepository {

    suspend fun getRandomImage() : Image

}
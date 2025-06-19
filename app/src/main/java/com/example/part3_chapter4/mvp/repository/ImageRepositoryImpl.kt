package com.example.part3_chapter4.mvp.repository

import com.example.part3_chapter4.ImageResponse
import com.example.part3_chapter4.RetrofitManager
import retrofit2.Call
import retrofit2.Response

class ImageRepositoryImpl : ImageRepository {

    override fun getRandomImage(callback: ImageRepository.Callback) {
        RetrofitManager.imageService.getRandomImage()
            .enqueue(object : retrofit2.Callback<ImageResponse> {
                override fun onResponse(
                    call: Call<ImageResponse?>,
                    response: Response<ImageResponse?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.loadImage(it.urls.regular, it.color)
                        }
                    }
                }

                override fun onFailure(
                    call: Call<ImageResponse?>,
                    t: Throwable
                ) {
                    TODO("Not yet implemented")
                }

            })
    }


}
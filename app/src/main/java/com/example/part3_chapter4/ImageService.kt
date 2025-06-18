package com.example.part3_chapter4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {

    @Headers("Authorization: Client-ID iokxVkE-gHghs4Q9eF268yBFolIvk6tXfaDIoXVqMUA")
    @GET("photos/random")
    fun getRandomImage() : Call<ImageResponse>

}
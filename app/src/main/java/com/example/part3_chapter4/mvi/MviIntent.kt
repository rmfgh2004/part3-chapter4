package com.example.part3_chapter4.mvi

sealed class MviIntent {
    object LoadImage : MviIntent()
}
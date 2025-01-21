package com.example.hershield.di



import com.example.hershield.VideoCallingApp
import com.example.hershield.connect.ConnectViewModel
import com.example.hershield.Video.VideoCallViewModel

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    factory {
        val app = androidContext().applicationContext as VideoCallingApp
        app.client
    }

    viewModelOf(::ConnectViewModel)
    viewModelOf(::VideoCallViewModel)
}
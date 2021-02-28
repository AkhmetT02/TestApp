package com.example.appfortest.di.modules

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideFragment(): Context {
        return context
    }
}
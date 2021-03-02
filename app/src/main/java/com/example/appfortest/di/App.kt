package com.example.appfortest.di

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.appfortest.di.modules.AppModule
import com.example.appfortest.di.modules.FragmentModule

class App() : Application() {


    companion object {
        private lateinit var appComponent: AppComponent

    }
    fun getAppComponent(): AppComponent = appComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .fragmentModule(FragmentModule(Fragment()))
            .build()
    }
}
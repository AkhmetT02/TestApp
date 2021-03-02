package com.example.appfortest.di.modules

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    fun provideFragment(): Fragment {
        return fragment
    }
}
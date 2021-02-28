package com.example.appfortest.di.modules

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.appfortest.adapters.BooksAdapter
import com.example.appfortest.fragments.BooksMenuFragment
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module(includes = [FragmentModule::class, PicassoModule::class])
class BooksAdapterModule {

    @Provides
    fun provideBooksAdapterForMenu(fragment: Fragment, picasso: Picasso): BooksAdapter {
        return BooksAdapter(fragment = fragment, picasso = picasso)
    }
}
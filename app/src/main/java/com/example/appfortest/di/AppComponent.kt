package com.example.appfortest.di

import com.example.appfortest.di.modules.*
import com.example.appfortest.fragments.BooksMenuFragment
import com.example.appfortest.fragments.DownloadedFragment
import com.example.appfortest.fragments.FavouriteFragment
import com.example.appfortest.network.RetrofitService
import com.example.appfortest.providers.BooksMenuProvider
import dagger.Component

@Component(modules = [AppModule::class, BooksAdapterModule::class, PicassoModule::class, RetrofitModule::class, RetrofitServiceModule::class])
interface AppComponent {

    //Fragments
    fun inject(booksMenuFragment: BooksMenuFragment)
    fun inject(downloadedFragment: DownloadedFragment)
    fun inject(favouriteFragment: FavouriteFragment)

    fun inject(provider: BooksMenuProvider)
}
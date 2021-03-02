package com.example.appfortest.di

import com.example.appfortest.di.modules.*
import com.example.appfortest.fragments.ui.book_menu.BooksMenuFragment
import com.example.appfortest.fragments.ui.DownloadedFragment
import com.example.appfortest.fragments.ui.FavouriteFragment
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
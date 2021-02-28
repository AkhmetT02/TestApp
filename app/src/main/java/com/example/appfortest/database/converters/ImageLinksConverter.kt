package com.example.appfortest.database.converters

import androidx.room.TypeConverter
import com.example.appfortest.models.ImageLinks
import com.google.gson.Gson


class ImageLinksConverter {

    @TypeConverter
    fun fromCountryLang(imageLinks: ImageLinks): String? {
        if (imageLinks == null) {
            return null
        }
        return Gson().toJson(imageLinks)
    }

    @TypeConverter
    fun toCountryLang(imageLinksAsString: String?): ImageLinks? {
        if (imageLinksAsString == null) {
            return null
        }
        val gson = Gson()
        return gson.fromJson(
            imageLinksAsString,
            ImageLinks::class.java
        )
    }
}
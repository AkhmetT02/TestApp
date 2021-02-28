package com.example.appfortest.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.appfortest.database.converters.ImageLinksConverter
import com.example.appfortest.database.converters.StringListConverter

@Entity(tableName = "books")
@TypeConverters(value = [ImageLinksConverter::class, StringListConverter::class])
data class BookModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val allowAnonLogging: Boolean?,
    val authors: List<String>?,
    val canonicalVolumeLink: String = "",
    val categories: List<String>?,
    val contentVersion: String? = "",
    val description: String? = "",
    val imageLinks: ImageLinks?,
    val infoLink: String? = "",
    val language: String? = "",
    val maturityRating: String? = "",
    val pageCount: Int? = 0,
    val previewLink: String? = "",
    val printType: String? = "",
    val publishedDate: String? = "",
    val publisher: String? = "",
    val subtitle: String? = "",
    val title: String = "",
    var downloaded: Boolean = false,
    var favourite: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BookModel

        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }
}

package com.example.artsy.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import com.example.artsy.data.model.ImageVariants

class Converters { // this is because the JSON file that we receive is nested, so needs to be converted

    @TypeConverter
    fun fromImageVariants(value: ImageVariants?): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toImageVariants(value: String): ImageVariants? {
        return Json.decodeFromString(value)
    }
}

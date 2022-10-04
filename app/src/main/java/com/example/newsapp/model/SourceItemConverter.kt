package com.example.newsapp.model

import androidx.room.TypeConverter
import org.json.JSONObject

class SourceItemConverter {

    @TypeConverter
    fun fromSource(source: SourcesItem): String {
        return JSONObject().apply {
//            put("name", source.name)
//            put("description", source.description)
//            put("language", source.language)
            put("id", source.id)
            //put("category", source.category)
           // put("url", source.url)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): SourcesItem {
        val json = JSONObject(source)
        return SourcesItem(
            id =  json.get("id").toString()
//            json.get("name").toString(),
//            json.get("description").toString(),
//            json.get("language").toString(),

//            json.get("category").toString(),
//            json.get("url").toString(),

            )
    }
}
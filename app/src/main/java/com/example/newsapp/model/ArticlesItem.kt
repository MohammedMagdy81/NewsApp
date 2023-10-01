package com.example.newsapp.model

import androidx.room.*
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NonNls

@Entity
@TypeConverters
data class ArticlesItem(

	@NonNls
	@ColumnInfo
	@PrimaryKey
	@field:SerializedName("publishedAt")
	val publishedAt: String,

	@ColumnInfo
	@field:SerializedName("author")
	val author: String? = null,

	@ColumnInfo
	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@ColumnInfo
	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val source: SourcesItem? = null,

	@ColumnInfo
	@field:SerializedName("title")
	val title: String? = null,

	@ColumnInfo
	@field:SerializedName("url")
	val url: String? = null,

	@ColumnInfo
	@field:SerializedName("content")
	val content: String? = null
)
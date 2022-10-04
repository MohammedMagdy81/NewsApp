package com.example.newsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NonNls

@Entity()
data class SourcesItem(

	@ColumnInfo
	@field:SerializedName("country")
	val country: String? = null,

	@ColumnInfo
	@field:SerializedName("name")
	val name: String? = null,

	@ColumnInfo
	@field:SerializedName("description")
	val description: String? = null,

	@ColumnInfo
	@field:SerializedName("language")
	val language: String? = null,

	@NonNls
	@PrimaryKey
	@ColumnInfo
	@field:SerializedName("id")
	val id: String,

	@ColumnInfo
	@field:SerializedName("category")
	val category: String? = null,

	@ColumnInfo
	@field:SerializedName("url")
	val url: String? = null
)
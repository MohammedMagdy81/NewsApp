package com.example.newsapp.localdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsModel(@ColumnInfo @PrimaryKey(autoGenerate = true) var id :Int?=null,
                     @ColumnInfo var title :String?=null,
                     @ColumnInfo var desc :String?=null,
                     @ColumnInfo var date :String?=null,
                     @ColumnInfo var urlToImage :String?=null ,
                     @ColumnInfo var url :String?=null
)
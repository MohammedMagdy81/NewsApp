package com.example.newsapp.internet

import android.content.Context
import android.net.ConnectivityManager

class NetworkAwereHandlerImp(val context: Context):NetworkAwereHandler {

    override fun isOnline(): Boolean {
        val cm =context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo()!!.isConnected();
    }

    companion object{

        var myInstance:NetworkAwereHandler?=null
        fun init(context: Context){
            myInstance=NetworkAwereHandlerImp(context)
        }

        fun getInstance():NetworkAwereHandler{
            return myInstance!!
        }
    }
}
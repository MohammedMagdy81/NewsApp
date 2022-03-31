package com.example.newsapp

import android.content.Context
import android.net.ConnectivityManager
import java.lang.Exception
import java.net.InetAddress

class NetworkAwareImp(val context: Context) : NetworkAware {

    companion object{
        var myInstance : NetworkAware?=null

        fun init(context: Context){
            myInstance = NetworkAwareImp(context)
        }
        fun getInstance():NetworkAware{
            return myInstance!!
        }
    }

    override fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo !=null &&
                connectivityManager.activeNetworkInfo!!.isConnected
    }
}
package com.example.todoapp.base

import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity :AppCompatActivity() {

    fun showDialog(title :String?=null,
                   message :String?=null,
                   posActionName :String?=null,
                   negActionName :String?=null,
                   posAction :DialogInterface.OnClickListener?=null,
                   negAction :DialogInterface.OnClickListener?=null,){
        val builder= AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(posActionName,posAction)
        builder.setNegativeButton(negActionName,negAction)
        builder.show()
    }
    fun makeToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}
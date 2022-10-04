package com.example.newsapp.base

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<DB:ViewDataBinding,VM:ViewModel>:AppCompatActivity() {

    lateinit var viewBinding:DB
    lateinit var viewModel:VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=DataBindingUtil.setContentView(this,getLayoutId())
        viewModel=initializeViewModel()

    }

    abstract fun initializeViewModel(): VM

    abstract fun getLayoutId(): Int

    fun showDialog(title :String?=null,
                   message :String?=null,
                   posActionName :String?=null,
                   negActionName :String?=null,
                   posAction : DialogInterface.OnClickListener?=null,
                   negAction :DialogInterface.OnClickListener?=null,){
        val builder= AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(posActionName,posAction)
        builder.setNegativeButton(negActionName,negAction)
        builder.show()
    }

    fun showDialog(title :Int?=null,
                   message :Int?=null,
                   posActionName :Int?=null,
                   negActionName :Int?=null,
                   posAction : DialogInterface.OnClickListener?=null,
                   negAction :DialogInterface.OnClickListener?=null,){
        val builder= AlertDialog.Builder(this)
        builder.setTitle(title!!)
        builder.setMessage(message!!)
        builder.setPositiveButton(posActionName!!,posAction)
        builder.setNegativeButton(negActionName!!,negAction)
        builder.show()
    }

    fun makeToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}
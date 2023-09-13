package com.example.compo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable

@Composable
fun ShowToast(context: Context, msg: String, duration: Int){

    Log.i("MyTag", "Show Toast method")
    Toast.makeText(context, msg, duration).show()
}
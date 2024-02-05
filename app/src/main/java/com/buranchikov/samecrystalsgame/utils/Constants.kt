package com.buranchikov.samecrystalsgame.utils

import android.widget.Toast
import com.buranchikov.samecrystalsgame.MainActivity

lateinit var APP_ACTIVITY:MainActivity

//field size (after rotate matrix row=4, columns=5)
val rowField:Int = 5
val columnField: Int = 4

// count of type crystals
val countTypeCrystals = 5

val TAG:String = "myLog"

fun showToast(str: String) {
    Toast.makeText(APP_ACTIVITY, str, Toast.LENGTH_SHORT).show()
}
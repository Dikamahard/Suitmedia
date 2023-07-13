package com.dikamahard.suitmedia

import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {


    fun isPalindrome(str: String): Boolean {
        val normalizedStr = str.replace(Regex("[^a-zA-Z0-9]"), "").lowercase()
        val reversedStr = normalizedStr.reversed()
        return normalizedStr == reversedStr
    }
}
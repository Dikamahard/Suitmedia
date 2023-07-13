package com.dikamahard.suitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dikamahard.suitmedia.data.preference.SessionPreferences
import com.dikamahard.suitmedia.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val pref = SessionPreferences(this)



        binding.btnCheck.setOnClickListener {
            if (binding.edPalindrome.text?.isNotEmpty() == true) {
                if (viewModel.isPalindrome(binding.edPalindrome.text.toString())) {
                    Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Empty Text", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNext.setOnClickListener {
            if(binding.edName.text?.isNotEmpty() == true) {
                pref.saveName(binding.edName.text.toString())
                val secondIntent = Intent(this, SecondActivity::class.java)
                startActivity(secondIntent)
            } else {
                Toast.makeText(this, "Name is empty", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
package com.dikamahard.suitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dikamahard.suitmedia.data.preference.SessionPreferences
import com.dikamahard.suitmedia.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: SecondViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SecondViewModel::class.java]
        val pref = SessionPreferences(this)


        binding.tvName.text = pref.getUserData(SessionPreferences.NAME)

        binding.btnChoose.setOnClickListener {
            startActivity(Intent(this@SecondActivity, ThirdActivity::class.java))
        }

        val choosenName = intent.getStringExtra(EXTRA_CHOOSEN)
        if (choosenName?.isNotEmpty() == true) {
            binding.tvSelectedName.text = choosenName
        }else {
            //binding.tvSelectedName.visibility = View.GONE
        }

    }


    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_CHOOSEN = "extra_choosen"
    }
}
package com.dikamahard.suitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dikamahard.suitmedia.databinding.ActivityThirdBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dikamahard.suitmedia.data.response.UserItem


class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: ThirdViewModel by viewModels {
            ViewModelFactory(this)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        viewModel.getAllUser()

        viewModel.user.observe(this) { user ->
            val adapter = UserAdapter()
            binding.rvUser.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }

            )
            adapter.submitData(lifecycle, user)
            adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: UserItem) {
                    val intent = Intent(this@ThirdActivity, SecondActivity::class.java)
                    intent.putExtra(SecondActivity.EXTRA_CHOOSEN, data.first_name + " " + data.last_name)
                    startActivity(intent)
                }

            })
        }

        binding.swipe.setOnRefreshListener {
            binding.rvUser.adapter = null
            binding.swipe.isRefreshing = false

            viewModel.getAllUser()
            viewModel.user.observe(this) { user ->
                val adapter = UserAdapter()
                binding.rvUser.adapter = adapter.withLoadStateFooter(
                    footer = LoadingStateAdapter {
                        adapter.retry()
                    }

                )
                adapter.submitData(lifecycle, user)
                adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: UserItem) {
                        val intent = Intent(this@ThirdActivity, SecondActivity::class.java)
                        intent.putExtra(SecondActivity.EXTRA_CHOOSEN, data.first_name + " " + data.last_name)
                        startActivity(intent)
                    }

                })
            }

        }

        viewModel.toastMessage.observe(this) { msg ->
            Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}
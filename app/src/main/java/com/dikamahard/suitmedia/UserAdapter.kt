package com.dikamahard.suitmedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dikamahard.suitmedia.data.response.UserItem
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter : PagingDataAdapter<UserItem, UserAdapter.ViewHolder>(DIFF_CALLBACK){

    private lateinit var onItemCLickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemCLickCallback = onItemClickCallback
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvItemName: TextView = view.findViewById(R.id.tv_name)
        private val tvItemEmail: TextView = view.findViewById(R.id.tv_email)
        private val ivItemPhoto: CircleImageView = view.findViewById(R.id.profile_image)

        fun bind(data: UserItem) {
            tvItemName.text = data.first_name + " " + data.last_name
            tvItemEmail.text = data.email
            Glide.with(ivItemPhoto).apply {
                load(data.avatar)
                    .into(ivItemPhoto)
            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
            holder.itemView.setOnClickListener {
                onItemCLickCallback.onItemClicked(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

}
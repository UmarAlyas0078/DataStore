package com.codesorbit.datastore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codesorbit.datastore.R
import com.codesorbit.datastore.loadImage
import com.codesorbit.datastore.model.Users

class UserListAdapter(var users: ArrayList<Users>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    fun updateUserList(newUserList: List<Users>) {
        users.clear()
        users.addAll(newUserList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.userAvatar)
        private val userName = view.findViewById<TextView>(R.id.userFullName)
        private val userEmail = view.findViewById<TextView>(R.id.userEmail)
        fun bind(user: Users) {
            userName.text = user.first_name + " " + user.last_name
            userEmail.text = user.email
            imageView.loadImage(user.avatar)
        }
    }
}
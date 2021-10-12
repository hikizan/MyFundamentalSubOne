package com.hikizan.myfundamentalssubone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hikizan.myfundamentalssubone.databinding.ItemRowUserBinding
import com.hikizan.myfundamentalssubone.model.GithubUser

class ListGithubUserAdapter(private val listGithubUser: ArrayList<GithubUser>) :
    RecyclerView.Adapter<ListGithubUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GithubUser)
    }

    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, username, photo) = listGithubUser[position]

        holder.binding.tvItemName.text = name
        holder.binding.tvItemUsername.text = username

        Glide.with(holder.itemView.context)
            .load(photo)
            .circleCrop()
            .into(holder.binding.imgItemPhoto)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listGithubUser[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listGithubUser.size
}
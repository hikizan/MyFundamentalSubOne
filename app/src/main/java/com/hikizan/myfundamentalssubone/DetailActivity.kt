package com.hikizan.myfundamentalssubone

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.hikizan.myfundamentalssubone.databinding.ActivityDetailBinding
import com.hikizan.myfundamentalssubone.model.GithubUser

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val githubUser: GithubUser? = intent.getParcelableExtra(EXTRA_DATA)
        binding.tvItemName.text = githubUser?.name
        binding.tvItemLocation.text = githubUser?.location
        binding.tvItemCompany.text = githubUser?.company
        binding.tvItemRepository.text = githubUser?.repository.toString()
        binding.tvItemFollowers.text = githubUser?.followers.toString()
        binding.tvItemFollowing.text = githubUser?.following.toString()

        Glide.with(this)
            .load(githubUser?.photo)
            .into(binding.imgItemPhoto)

        supportActionBar?.title = githubUser?.username

        numberFont()
    }

    private fun numberFont() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.tvItemFollowers.textSize = 30F
            binding.tvItemFollowing.textSize = 30F
            binding.tvItemRepository.textSize = 30F

        } else {
            binding.tvItemFollowers.textSize = 24F
            binding.tvItemFollowing.textSize = 24F
            binding.tvItemRepository.textSize = 24F
        }
    }
}
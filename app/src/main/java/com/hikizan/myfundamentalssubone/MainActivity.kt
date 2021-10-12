package com.hikizan.myfundamentalssubone

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hikizan.myfundamentalssubone.adapter.ListGithubUserAdapter
import com.hikizan.myfundamentalssubone.model.GithubUser

class MainActivity : AppCompatActivity() {

    private lateinit var rvGithubUser: RecyclerView
    private val list = ArrayList<GithubUser>()
    private var title = "List Github User"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGithubUser = findViewById(R.id.rv_GithubUser)
        rvGithubUser.setHasFixedSize(true)

        list.addAll(listGithubUsers)
        showRecyclerList()
        setOnActionBarTitle(title)

    }

    private fun setOnActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private val listGithubUsers: ArrayList<GithubUser>
        @SuppressLint("Recycle")
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataUsername = resources.getStringArray(R.array.data_username)
            val dataLocation = resources.getStringArray(R.array.data_location)
            val dataRepository = resources.getIntArray(R.array.data_repository)
            val dataCompany = resources.getStringArray(R.array.data_company)
            val dataFollowers = resources.getIntArray(R.array.data_followers)
            val dataFollowing = resources.getIntArray(R.array.data_following)
            val dataPhoto = resources.obtainTypedArray(R.array.data_avatar)
            val listGithubUser = ArrayList<GithubUser>()
            for (i in dataName.indices) {
                val githubUser = GithubUser(
                    dataName[i],
                    dataUsername[i],
                    dataPhoto.getResourceId(i, -1),
                    dataLocation[i],
                    dataRepository[i],
                    dataCompany[i],
                    dataFollowers[i],
                    dataFollowing[i],
                )

                listGithubUser.add(githubUser)
            }
            return listGithubUser
        }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            rvGithubUser.layoutManager = GridLayoutManager(this,2)
        }else{
            rvGithubUser.layoutManager = LinearLayoutManager(this)
        }
        val listGithubUserAdapter = ListGithubUserAdapter(list)
        rvGithubUser.adapter = listGithubUserAdapter

        listGithubUserAdapter.setOnItemClickCallback(object: ListGithubUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GithubUser) {
                showSelectedGithubUser(data)
            }
        })
    }

    private fun showSelectedGithubUser(githubuser: GithubUser){
        val moveWithDataParcel = Intent(this, DetailActivity::class.java)
        moveWithDataParcel.putExtra(DetailActivity.EXTRA_DATA, githubuser)
        startActivity(moveWithDataParcel)
    }
}
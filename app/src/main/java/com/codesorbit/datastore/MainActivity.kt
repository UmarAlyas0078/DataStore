package com.codesorbit.datastore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.codesorbit.datastore.PrefrenseData.UserPrefrence
import com.codesorbit.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var userPrefrence: UserPrefrence
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
        userPrefrence = UserPrefrence(applicationContext)
        viewBinding.btnAdd.setOnClickListener {
            lifecycleScope.launch {
                userPrefrence.saveBookMarks("Helo", true)
            }
        }
        userPrefrence.bookmarks.asLiveData().observe(this, Observer {
            if (it != null) {
                viewBinding.tvShow.text = it
            }
        })
        userPrefrence.booleanValue.asLiveData().observe(this, Observer {
            if (it != null) {
                viewBinding.tvBool.text = it.toString()
            }
        })
    }
}
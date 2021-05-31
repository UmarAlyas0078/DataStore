package com.codesorbit.datastore

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.codesorbit.datastore.PrefrenseData.UserPrefrence
import com.codesorbit.datastore.adapter.UserListAdapter
import com.codesorbit.datastore.databinding.ActivityMainUserListBinding
import com.codesorbit.datastore.viewmodel.UserListViewModel

class MainActivity : AppCompatActivity() {
    // private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewBinding: ActivityMainUserListBinding
    private lateinit var userPrefrence: UserPrefrence
    private var valueBoolean = false
    private lateinit var userListViewModel: UserListViewModel
    private val userListAdapter = UserListAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainUserListBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
        userListViewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        userListViewModel.refresh()
        viewBinding.usersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }
        observeViewModel()
        // val sharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        //val sharePrefrenceEditor: SharedPreferences.Editor = sharedPreferences.edit()
        //val isNightModeOn = sharedPreferences.getBoolean("NightMode", false)

/*
        userPrefrence = UserPrefrence(applicationContext)
        viewBinding.btnAdd.setOnClickListener {
            lifecycleScope.launch {
                userPrefrence.saveBookMarks("Helo", true)
            }
        }
        userPrefrence.nightModeValue.asLiveData().observe(this, Observer {
            if (it != null) {
                valueBoolean = it
                Log.d("TAG", "onValue: " + valueBoolean)
                if (valueBoolean) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    viewBinding.btnSwitch.isChecked = valueBoolean
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    viewBinding.btnSwitch.isChecked = valueBoolean
                }
            }
        })
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
     *//*  val mode = applicationContext.resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)
        when(mode){
            Configuration.UI_MODE_NIGHT_YES->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                viewBinding.btnSwitch.isChecked = true
                lifecycleScope.launch {
                    userPrefrence.saveNightMode(true)
                }
            }
            Configuration.UI_MODE_NIGHT_NO->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                viewBinding.btnSwitch.isChecked = false
                lifecycleScope.launch {
                    userPrefrence.saveNightMode(false)
                }
            }
        }*//*
        viewBinding.btnSwitch.setOnClickListener {
            if (valueBoolean) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                lifecycleScope.launch {
                    userPrefrence.saveNightMode(false)
                }
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                lifecycleScope.launch {
                    userPrefrence.saveNightMode(true)
                }
            }
        }*/
    }

    private fun observeViewModel() {
        userListViewModel.users.observe(this, { countries->
            countries.let {
                viewBinding.usersList.visibility = View.VISIBLE
                userListAdapter.updateUserList(it)
            }
        })
    }
}
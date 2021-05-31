package com.codesorbit.datastore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codesorbit.datastore.model.Users
import com.codesorbit.datastore.webservices.UserServicesClient
import kotlinx.coroutines.*

class UserListViewModel : ViewModel() {
    val userServicesClient = UserServicesClient().getServicesClient()
    var job: Job? = null
    val users = MutableLiveData<List<Users>>()
    val userloader = MutableLiveData<String?>()
    var loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(s: String) {
        userloader.value = s
        loading.value = false
    }

    fun refresh() {
        fetchUser()
    }

    private fun fetchUser() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = userServicesClient.getUserList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    users.value = response.body()?.data
                    userloader.value = null
                    loading.value = false
                } else {
                    onError(response.message())
                }
            }
        }
        userloader.value = ""
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
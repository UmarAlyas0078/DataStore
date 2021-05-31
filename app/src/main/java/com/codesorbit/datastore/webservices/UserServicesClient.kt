package com.codesorbit.datastore.webservices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserServicesClient {
    private var BASE_URL = "https://reqres.in/api/"
    fun getServicesClient(): UserApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }
}
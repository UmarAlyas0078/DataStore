package com.codesorbit.datastore.webservices

import com.codesorbit.datastore.model.Responses
import com.codesorbit.datastore.model.Users
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUserList(): Response<Responses>
}
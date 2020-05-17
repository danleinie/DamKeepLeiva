package com.salesianostriana.damkeepapp.api

import com.salesianostriana.damkeepapp.api.requests.UserLoginRequest
import com.salesianostriana.damkeepapp.api.requests.UserRegisterRequest
import com.salesianostriana.damkeepapp.api.responses.LoginResponse
import com.salesianostriana.damkeepapp.api.responses.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("auth/login")
    fun login (@Body user: UserLoginRequest) : Call<LoginResponse>

    @POST("user/")
    fun register(@Body user : UserRegisterRequest) : Call<User>
}
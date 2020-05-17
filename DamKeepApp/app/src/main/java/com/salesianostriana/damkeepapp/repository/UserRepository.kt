package com.salesianostriana.damkeepapp.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.salesianostriana.damkeepapp.api.UserService
import com.salesianostriana.damkeepapp.api.requests.UserLoginRequest
import com.salesianostriana.damkeepapp.api.requests.UserRegisterRequest
import com.salesianostriana.damkeepapp.api.responses.LoginResponse
import com.salesianostriana.damkeepapp.api.responses.User
import com.salesianostriana.damkeepapp.di.MyApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(var userService: UserService) {

    fun login(user : UserLoginRequest) : MutableLiveData<LoginResponse>{
        var data : MutableLiveData<LoginResponse> = MutableLiveData<LoginResponse>()

        val call : Call<LoginResponse> = userService.login(user)
        call.enqueue(object : Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada de login: " + t.message, Toast.LENGTH_LONG).show()
                Log.i("errorlogin", t.message)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) data.value = response.body()
            }

        })
        return data
    }

    fun register(user : UserRegisterRequest) : MutableLiveData<User>{
        var data : MutableLiveData<User> = MutableLiveData<User>()

        val call : Call<User> = userService.register(user)
        call.enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada de registro: " + t.message, Toast.LENGTH_LONG).show()
                Log.i("errorregistro", t.message)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) data.value = response.body()
            }

        })
        return data
    }

}
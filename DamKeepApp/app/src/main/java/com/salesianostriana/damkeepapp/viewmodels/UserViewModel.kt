package com.salesianostriana.damkeepapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.salesianostriana.damkeepapp.api.requests.UserLoginRequest
import com.salesianostriana.damkeepapp.api.requests.UserRegisterRequest
import com.salesianostriana.damkeepapp.api.responses.LoginResponse
import com.salesianostriana.damkeepapp.api.responses.User
import com.salesianostriana.damkeepapp.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(var userRepository: UserRepository): ViewModel() {

    fun login(user : UserLoginRequest) : LiveData<LoginResponse> = userRepository.login(user)
    fun register(user : UserRegisterRequest) : LiveData<User> = userRepository.register(user)
}
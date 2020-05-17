package com.salesianostriana.damkeepapp.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.salesianostriana.damkeepapp.MainActivity
import com.salesianostriana.damkeepapp.R
import com.salesianostriana.damkeepapp.api.TokenInterceptor
import com.salesianostriana.damkeepapp.api.requests.UserLoginRequest
import com.salesianostriana.damkeepapp.common.Constantes
import com.salesianostriana.damkeepapp.common.SharedPreferencesManager
import com.salesianostriana.damkeepapp.di.MyApp
import com.salesianostriana.damkeepapp.viewmodels.UserViewModel
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject lateinit var userViewModel: UserViewModel
    @Inject lateinit var tokenInterceptor: TokenInterceptor
    //@Inject lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username : EditText = findViewById(R.id.username)
        val password : EditText = findViewById(R.id.password)
        val buttonLogin : Button = findViewById(R.id.login)
        val buttonRegister : Button = findViewById(R.id.buttonToRegister)

        (applicationContext as MyApp).appComponent.inject(this)

        buttonRegister.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        })

        buttonLogin.setOnClickListener(View.OnClickListener {

            var user = UserLoginRequest(username.text.toString(),password.text.toString())

            userViewModel.login(user).observeForever(Observer {
                SharedPreferencesManager.setStringValue(Constantes.TOKEN,it.token)
                tokenInterceptor.token = it.token
                startActivity(Intent(this,MainActivity::class.java))
                //var hola : String? = SharedPreferencesManager.getStringValue("TOKEN")
                //Log.i("averr","" + hola)
            })
        })

    }
}

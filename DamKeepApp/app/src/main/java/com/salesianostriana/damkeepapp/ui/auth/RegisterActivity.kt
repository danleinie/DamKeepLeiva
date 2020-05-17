package com.salesianostriana.damkeepapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.salesianostriana.damkeepapp.R
import com.salesianostriana.damkeepapp.api.requests.UserRegisterRequest
import com.salesianostriana.damkeepapp.di.MyApp
import com.salesianostriana.damkeepapp.viewmodels.UserViewModel
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        (applicationContext as MyApp).appComponent.inject(this)

        val username : EditText = findViewById(R.id.usernameRegister)
        val password : EditText = findViewById(R.id.passwordRegister)
        val fullname : EditText = findViewById(R.id.fullname)
        val password2 : EditText = findViewById(R.id.confirmPassword)
        val buttonRegister : Button = findViewById(R.id.buttonRegister)

        buttonRegister.setOnClickListener(View.OnClickListener {
            var user = UserRegisterRequest(fullname.text.toString(),password.text.toString(),password2.toString(),username.text.toString())
            userViewModel.register(user).observeForever(Observer {
                finish()
            })
        })
    }
}

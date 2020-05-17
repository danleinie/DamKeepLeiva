package com.salesianostriana.damkeepapp.di
import com.salesianostriana.damkeepapp.api.NetworkModule
import com.salesianostriana.damkeepapp.ui.AddNotaActivity
import com.salesianostriana.damkeepapp.ui.DetalleActivity
import com.salesianostriana.damkeepapp.ui.auth.LoginActivity
import com.salesianostriana.damkeepapp.ui.auth.RegisterActivity
import com.salesianostriana.damkeepapp.ui.listadonotas.NotaFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [NetworkModule::class])
interface AplicationComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(notaFragment: NotaFragment)
    fun inject(detalleActivity: DetalleActivity)
    fun inject(addNotaActivity: AddNotaActivity)
    fun inject(registerActivity: RegisterActivity)
}
package com.salesianostriana.dam.Damkeep

import com.salesianostriana.dam.Damkeep.models.Nota
import com.salesianostriana.dam.Damkeep.models.User
import com.salesianostriana.dam.Damkeep.repositorios.NotaRepository
import com.salesianostriana.dam.Damkeep.repositorios.UserRepository
import com.salesianostriana.dam.Damkeep.services.UserService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@SpringBootApplication
class DamkeepApplication

fun main(args: Array<String>) {
	runApplication<DamkeepApplication>(*args)
}

@Component
class InitDataComponent(
		val notaRepository: NotaRepository,
		val userRepository: UserRepository,
		val encoder : PasswordEncoder,
		val userService: UserService
){
	@PostConstruct
	fun initData(){

		val user1 = User("xleiiva",encoder.encode("1234"),"Daniel Leiva","USER")
		val user2 = User("cads",encoder.encode("1234"),"Jesús Ceacero","USER")
		userRepository.save(user1)
		userRepository.save(user2)


		val nota1 = Nota("Primera nota", "Esta es mi primera nota en la aplicación desde la clase NotaRepository",usuario = user1)
		val nota3 = Nota("Tercera nota", "Esta es mi tercera nota en la aplicación desde la clase NotaRepository",usuario = user1)
		val nota2 = Nota("Segunda nota", "Nota de prueba creada en la clase NotaRepository",usuario = user2)
		notaRepository.save(nota1)
		notaRepository.save(nota2)
		notaRepository.save(nota3)
	}

}
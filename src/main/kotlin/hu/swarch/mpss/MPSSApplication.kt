package hu.swarch.mpss

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MPSSApplication

fun main(args: Array<String>) {
	runApplication<MPSSApplication>(*args)
}

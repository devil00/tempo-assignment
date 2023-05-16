package com.example.demo

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition
class TempoApplication

fun main(args: Array<String>) {
	runApplication<TempoApplication>(*args)
}

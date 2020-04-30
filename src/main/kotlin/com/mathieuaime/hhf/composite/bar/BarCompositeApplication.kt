package com.mathieuaime.hhf.composite.bar

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = Info(title = "Bar API", version = "1.0", description = "Documentation Bar API v1.0"))
open class BarCompositeApplication

fun main(args: Array<String>) {
    runApplication<BarCompositeApplication>(*args)
}
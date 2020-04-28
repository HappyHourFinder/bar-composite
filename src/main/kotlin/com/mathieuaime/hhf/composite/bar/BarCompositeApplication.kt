package com.mathieuaime.hhf.composite.bar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
open class BarCompositeApplication

fun main(args: Array<String>) {
    runApplication<BarCompositeApplication>(*args)
}
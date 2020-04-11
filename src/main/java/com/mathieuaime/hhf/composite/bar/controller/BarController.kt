package com.mathieuaime.hhf.composite.bar.controller

import com.mathieuaime.hhf.composite.bar.model.BarAggregate
import com.mathieuaime.hhf.composite.bar.service.BarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class BarController(private var barService: BarService) {
    @GetMapping
    fun getBar(): Flux<BarAggregate> = barService.get()

    @GetMapping("/{uuid}")
    fun getBar(@PathVariable("uuid") uuid: String): Mono<BarAggregate> = barService.getByUuid(uuid)
}

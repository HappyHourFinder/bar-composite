package com.mathieuaime.hhf.bar.controller

import com.mathieuaime.hhf.bar.model.BarAggregate
import com.mathieuaime.hhf.bar.service.BarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class BarController(private var barService: BarService) {
    @GetMapping("/{id}")
    fun getBar(@PathVariable("id") uuid: Int): Mono<BarAggregate> {
        return barService.getById(uuid)
    }
}

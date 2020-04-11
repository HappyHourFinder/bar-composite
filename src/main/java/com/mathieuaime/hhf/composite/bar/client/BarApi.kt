package com.mathieuaime.hhf.composite.bar.client

import com.mathieuaime.hhf.composite.bar.model.Bar
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@FeignClient(value = "bar-service", fallback = BarApiFallback::class)
interface BarApi {
    @GetMapping
    fun get(): Flux<Bar>

    @GetMapping("/{uuid}")
    fun getByUuid(@RequestParam("uuid") uuid: String): Mono<Bar>
}

private class BarApiFallback : BarApi {
    override fun get(): Flux<Bar> = Flux.empty()

    override fun getByUuid(uuid: String): Mono<Bar> = Mono.empty()
}
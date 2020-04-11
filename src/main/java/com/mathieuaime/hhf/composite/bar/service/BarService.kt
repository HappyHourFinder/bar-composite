package com.mathieuaime.hhf.composite.bar.service

import com.mathieuaime.hhf.composite.bar.client.BarApi
import com.mathieuaime.hhf.composite.bar.client.HappyHourApi
import com.mathieuaime.hhf.composite.bar.model.Bar
import com.mathieuaime.hhf.composite.bar.model.BarAggregate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BarService(private val barApi: BarApi, private val happyHourApi: HappyHourApi) {
    fun get(): Flux<BarAggregate> = barApi.get().map(this::compose)

    fun getByUuid(uuid: String): Mono<BarAggregate> = barApi.getByUuid(uuid).map(this::compose)

    private fun compose(bar: Bar) = BarAggregate(bar, happyHourApi.getByBarUuid(bar.uuid))
}
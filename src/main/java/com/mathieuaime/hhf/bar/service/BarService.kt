package com.mathieuaime.hhf.bar.service

import com.mathieuaime.hhf.bar.client.BarApi
import com.mathieuaime.hhf.bar.client.HappyHourApi
import com.mathieuaime.hhf.bar.model.BarAggregate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BarService(private val barApi: BarApi, private val happyHourApi: HappyHourApi) {
    fun getById(id: Int): Mono<BarAggregate> {
        val bar = barApi.getById(id)
        val happyHour = happyHourApi.getByBarId(id)

        return Mono.just(BarAggregate(bar, happyHour))
    }
}
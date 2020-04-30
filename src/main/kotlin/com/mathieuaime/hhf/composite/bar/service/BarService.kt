package com.mathieuaime.hhf.composite.bar.service

import com.mathieuaime.hhf.composite.bar.client.BarApi
import com.mathieuaime.hhf.composite.bar.client.HappyHourApi
import com.mathieuaime.hhf.composite.bar.model.Bar
import com.mathieuaime.hhf.composite.bar.model.BarAggregate
import org.springframework.stereotype.Service
import java.util.*

@Service
class BarService(private val barApi: BarApi, private val happyHourApi: HappyHourApi) {
    fun get() = barApi.get().map(this::compose)

    fun getByUuid(uuid: String): Optional<BarAggregate> = Optional.ofNullable(barApi.getByUuid(uuid)).map(this::compose)

    fun save(bar: Bar) = bar

    fun delete(uuid: String) {}

    private fun compose(bar: Bar) = BarAggregate(bar, happyHourApi.getByBarUuid(bar.uuid))
}
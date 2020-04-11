package com.mathieuaime.hhf.composite.bar.client

import com.mathieuaime.hhf.composite.bar.model.HappyHour
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "happy-hour-service", fallback = HappyHourApiFallback::class)
interface HappyHourApi {
    @GetMapping("/?barUuid={barUuid}")
    fun getByBarUuid(@RequestParam barUuid: String): List<HappyHour>
}

private class HappyHourApiFallback : HappyHourApi {
    override fun getByBarUuid(barUuid: String): List<HappyHour> = emptyList()
}
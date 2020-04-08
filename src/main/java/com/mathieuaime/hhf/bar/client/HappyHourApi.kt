package com.mathieuaime.hhf.bar.client

import com.mathieuaime.hhf.bar.model.HappyHour
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "happy-hour-service", fallback = HappyHourApiFallback::class)
interface HappyHourApi {
    @GetMapping("/?barId={barId}")
    fun getByBarId(@RequestParam barId: Int): List<HappyHour>
}

private class HappyHourApiFallback : HappyHourApi {
    override fun getByBarId(barId: Int): List<HappyHour> {
        return emptyList()
    }
}
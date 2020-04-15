package com.mathieuaime.hhf.composite.bar.client

import com.mathieuaime.hhf.composite.bar.model.HappyHour
import feign.Headers
import feign.Param
import feign.RequestLine
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(value = "happy-hour-service", fallback = HappyHourApiFallback::class)
@Headers("Content-Type: application/json")
interface HappyHourApi {
    @RequestLine("GET /?barUuid={barUuid}")
    fun getByBarUuid(@Param("barUuid") barUuid: String): List<HappyHour>
}

private class HappyHourApiFallback : HappyHourApi {
    override fun getByBarUuid(barUuid: String): List<HappyHour> = emptyList()
}
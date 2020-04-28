package com.mathieuaime.hhf.composite.bar.client

import com.mathieuaime.hhf.composite.bar.model.Bar
import feign.Headers
import feign.Param
import feign.RequestLine
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(value = "bar-service", fallback = BarApiFallback::class)
@Headers("Content-Type: application/json")
interface BarApi {
    @RequestLine("GET /bars")
    fun get(): List<Bar>

    @RequestLine("GET /bars/{uuid}")
    fun getByUuid(@Param("uuid") uuid: String): Bar?
}

private class BarApiFallback : BarApi {
    override fun get(): List<Bar> = emptyList()

    override fun getByUuid(uuid: String): Bar? = null
}
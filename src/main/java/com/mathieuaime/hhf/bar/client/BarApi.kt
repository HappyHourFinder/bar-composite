package com.mathieuaime.hhf.bar.client

import com.mathieuaime.hhf.bar.model.Bar
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "bar-service", fallback = BarApiFallback::class)
interface BarApi {
    @GetMapping("/{id}")
    fun getById(id: Int): Bar
}

private class BarApiFallback : BarApi {
    override fun getById(id: Int): Bar {
        return Bar(id, "")
    }
}
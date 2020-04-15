package com.mathieuaime.hhf.composite.bar.controller

import com.mathieuaime.hhf.composite.bar.model.Bar
import com.mathieuaime.hhf.composite.bar.service.BarService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class BarController(private var barService: BarService) {
    @GetMapping
    fun getBar() = barService.get()

    @GetMapping("/{uuid}")
    fun getBar(@PathVariable("uuid") uuid: String) = barService.getByUuid(uuid)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun saveBar(@RequestBody bar: Bar) = barService.save(bar)

    @DeleteMapping("/{uuid}")
    fun deleteBar(@PathVariable("uuid") uuid: String) = barService.delete(uuid)
}

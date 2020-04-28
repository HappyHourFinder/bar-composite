package com.mathieuaime.hhf.composite.bar.controller

import com.mathieuaime.hhf.composite.bar.model.Bar
import com.mathieuaime.hhf.composite.bar.service.BarService
import com.mathieuaime.hhf.composite.bar.service.HappyHourService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/bars")
@RestController
class BarController(private var barService: BarService, private val happyHourService: HappyHourService) {
    @GetMapping
    fun getBar() = barService.get()

    @GetMapping("/{uuid}")
    fun getBar(@PathVariable("uuid") uuid: String) = ResponseEntity.of(barService.getByUuid(uuid))

    @GetMapping("/{barUuid}/happyhours")
    fun getHappyHours(@PathVariable("barUuid") barUuid: String) = happyHourService.getByBarUuid(barUuid)

    @GetMapping("/{barUuid}/happyhours/{uuid}")
    fun getHappyHour(@PathVariable("uuid") uuid: String, @PathVariable("barUuid") barUuid: String) =
            ResponseEntity.of(happyHourService.getByUuidAndBarUuid(uuid, barUuid))

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun saveBar(@RequestBody bar: Bar) = barService.save(bar)

    @DeleteMapping("/{uuid}")
    fun deleteBar(@PathVariable("uuid") uuid: String) = barService.delete(uuid)
}

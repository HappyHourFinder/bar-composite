package com.mathieuaime.hhf.composite.bar.service

import com.mathieuaime.hhf.composite.bar.client.HappyHourApi
import com.mathieuaime.hhf.composite.bar.model.HappyHour
import org.springframework.stereotype.Service
import java.util.*

@Service
class HappyHourService(private val happyHourApi: HappyHourApi) {
    fun getByBarUuid(barUuid: String): List<HappyHour> = happyHourApi.getByBarUuid(barUuid)

    fun getByUuidAndBarUuid(uuid: String, barUuid: String) =
            Optional.ofNullable(happyHourApi.getByUuidAndBarUuid(uuid, barUuid))
}
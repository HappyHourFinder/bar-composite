package com.mathieuaime.hhf.composite.bar.feign

import feign.Response
import feign.codec.ErrorDecoder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class FeignErrorDecoder : ErrorDecoder {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun decode(methodKey: String, response: Response): Exception {
        logger.error("Status code " + response.status().toString() + ", methodKey = " + methodKey)
        return ResponseStatusException(HttpStatus.valueOf(response.status()), if (response.status() != 500) response.reason() else "Internal Server Error")
    }
}
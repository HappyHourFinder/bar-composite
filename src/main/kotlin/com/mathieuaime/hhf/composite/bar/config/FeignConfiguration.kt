package com.mathieuaime.hhf.composite.bar.config

import com.mathieuaime.hhf.composite.bar.feign.FeignErrorDecoder
import feign.Contract
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class FeignConfiguration {
    @Bean
    fun feignContract(): Contract = Contract.Default()

    @Bean
    fun errorDecoder(): ErrorDecoder = FeignErrorDecoder()
}
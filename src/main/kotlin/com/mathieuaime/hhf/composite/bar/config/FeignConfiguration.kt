package com.mathieuaime.hhf.composite.bar.config

import com.mathieuaime.hhf.composite.bar.feign.FeignErrorDecoder
import feign.Contract
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
open class FeignConfiguration {
    @Bean
    open fun feignContract(): Contract = Contract.Default()

    @Bean
    open fun errorDecoder(): ErrorDecoder = FeignErrorDecoder()
}
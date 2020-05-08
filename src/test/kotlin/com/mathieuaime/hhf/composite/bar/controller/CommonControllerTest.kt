package com.mathieuaime.hhf.composite.bar.controller

import com.netflix.loadbalancer.Server
import com.netflix.loadbalancer.ServerList
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.cloud.netflix.ribbon.StaticServerList
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 18090)
@AutoConfigureJsonTesters
@ContextConfiguration(classes = [CommonControllerTest.LocalRibbonClientConfiguration::class])
internal abstract class CommonControllerTest {
    @TestConfiguration
    class LocalRibbonClientConfiguration {
        @Bean
        fun ribbonServerList(): ServerList<Server> = StaticServerList(Server("localhost", 18090))
    }
}
package com.mathieuaime.hhf.composite.bar.controller

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.netflix.loadbalancer.Server
import com.netflix.loadbalancer.ServerList
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.cloud.netflix.ribbon.StaticServerList
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ContextConfiguration(classes = [CommonControllerTest.LocalRibbonClientConfiguration::class])
internal abstract class CommonControllerTest {
    private lateinit var barMock: WireMockServer
    private lateinit var happyHourMock: WireMockServer

    @BeforeEach
    fun proxyToWireMock() {
        barMock = WireMockServer(WireMockConfiguration.options().port(9080).usingFilesUnderClasspath("bar"))
        barMock.start()
        happyHourMock = WireMockServer(WireMockConfiguration.options().port(9081).usingFilesUnderClasspath("happy-hour"))
        happyHourMock.start()
    }

    @AfterEach
    fun noMoreWireMock() {
        barMock.stop()
        happyHourMock.stop()
    }

    @TestConfiguration
    class LocalRibbonClientConfiguration {
        @Bean
        fun ribbonServerList(): ServerList<Server> {
            return StaticServerList(Server("localhost", 9080), Server("localhost", 9081))
        }
    }
}
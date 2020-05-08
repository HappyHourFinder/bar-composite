package com.mathieuaime.hhf.composite.bar.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mathieuaime.hhf.composite.bar.model.Bar
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

internal class BarControllerTest : CommonControllerTest() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun getBars() {
        mockMvc.perform(MockMvcRequestBuilders.get("/bars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize<Any>(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bar.uuid", Matchers.`is`("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bar.name", Matchers.`is`("Bar")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bar.latitude").value(Matchers.`is`(1.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bar.longitude").value(Matchers.`is`(2.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].happyHour[0].uuid").value(Matchers.`is`("hh-1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].happyHour[0].begin").value(Matchers.`is`("10:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].happyHour[0].end").value(Matchers.`is`("11:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bar.uuid", Matchers.`is`("2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bar.name", Matchers.`is`("Other bar")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bar.latitude").value(Matchers.`is`(2.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bar.longitude").value(Matchers.`is`(3.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].happyHour[0].uuid").value(Matchers.`is`("hh-2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].happyHour[0].begin").value(Matchers.`is`("11:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].happyHour[0].end").value(Matchers.`is`("12:00:00")))
    }

    @Test
    fun getBar() {
        mockMvc.perform(MockMvcRequestBuilders.get("/bars/{uuid}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.bar.uuid", Matchers.`is`("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bar.name", Matchers.`is`("Bar")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bar.latitude").value(Matchers.`is`(1.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bar.longitude").value(Matchers.`is`(2.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$.happyHour[0].uuid").value(Matchers.`is`("hh-1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.happyHour[0].begin").value(Matchers.`is`("10:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.happyHour[0].end").value(Matchers.`is`("11:00:00")))
    }

    @Test
    fun getBarNotFound() {
        mockMvc.perform(MockMvcRequestBuilders.get("/bars/{uuid}", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun saveBarUnauthorized() {
        val bar = Bar("1", "Bar 1", 1.0, 2.0)

        mockMvc.perform(MockMvcRequestBuilders.post("/bars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bar)))
                .andExpect(MockMvcResultMatchers.status().isForbidden)
    }

    @Test
    fun saveBar() {
        val bar = Bar("1", "Bar 1", 1.0, 2.0)

        mockMvc.perform(MockMvcRequestBuilders.post("/bars")
                .with(jwt().jwt { it.subject("Subject A") })
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bar)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid", Matchers.`is`("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.`is`("Bar 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude").value(Matchers.`is`(1.0), Double::class.java))
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude").value(Matchers.`is`(2.0), Double::class.java))
    }

    @Test
    fun deleteBarUnauthorized() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/bars/{uuid}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden)
    }

    @Test
    fun deleteBar() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/bars/{uuid}", "1")
                .with(jwt().jwt { it.subject("Subject A") })
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
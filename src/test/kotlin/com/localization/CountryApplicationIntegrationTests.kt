package com.localization

import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class CountryApplicationIntegrationTests(@Autowired val mockMvc: MockMvc) {

    @Test
    fun checkResponseCodeWithCorrectData() {
        mockMvc.perform(get("/countries/IN?language=en")).andExpect(status().isOk)
        mockMvc.perform(get("/countries/AU?language=en")).andExpect(status().isOk)
        mockMvc.perform(get("/countries/UA?language=en")).andExpect(status().isOk)
    }

    @Test
    fun checkResponseCodeWithIncorrectData() {
        mockMvc.perform(get("/countries/OOO?language=en")).andExpect(status().isNotFound)
        mockMvc.perform(get("/countries/AU?language=as")).andExpect(status().isNotFound)
        mockMvc.perform(get("/countries/UADS?language=english")).andExpect(status().isNotFound)
    }

    @Test
    fun checkResponseDataTypeWithCorrectData() {
        mockMvc.perform(get("/countries/IN?language=en")).andExpect(content().contentType("application/json"))
        mockMvc.perform(get("/countries/AU?language=en")).andExpect(content().contentType("application/json"))
        mockMvc.perform(get("/countries/UA?language=en")).andExpect(content().contentType("application/json"))
    }

    @Test
    fun checkResponseDataTypeWithIncorrectData() {
        mockMvc.perform(get("/countries/OOO?language=en")).andExpect(content().contentType("application/json"))
        mockMvc.perform(get("/countries/AU?language=as")).andExpect(content().contentType("application/json"))
        mockMvc.perform(get("/countries/UADS?language=english")).andExpect(content().contentType("application/json"))
    }

    @Test
    fun checkResponseStringWithCorrectData() {
        mockMvc.perform(get("/countries/IN?language=en")).andExpect(content().string(containsString("India")))
        mockMvc.perform(get("/countries/AU?language=en")).andExpect(content().string(containsString("Australia")))
        mockMvc.perform(get("/countries/UA?language=en")).andExpect(content().string(containsString("Ukraine")))
    }

    @Test
    fun checkResponseStringWithIncorrectData() {
        mockMvc.perform(get("/countries/OOO?language=en")).andExpect(content().string(containsString("Can't get name of country by iso: OOO and language: en")))
        mockMvc.perform(get("/countries/AU?language=as")).andExpect(content().string(containsString("Can't get name of country by iso: AU and language: as")))
        mockMvc.perform(get("/countries/UADS?language=english")).andExpect(content().string(containsString("Can't get name of country by iso: UADS and language: english")))
    }
}

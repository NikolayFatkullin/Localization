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
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string(containsString("India")))
    }

    @Test
    fun checkResponseCodeWithIncorrectIso() {
        mockMvc.perform(get("/countries/OOO?language=en")).andExpect(status().isBadRequest)
            .andExpect(content().contentType("application/json"))
            .andExpect(
                content().string(
                    containsString(
                        "Incorrect iso code of country: OOO"
                    )
                )
            )

    }

    @Test
    fun checkResponseDataTypeWithIncorrectLanguage() {
        mockMvc.perform(get("/countries/UA?language=english"))
            .andExpect(status().isBadRequest)
            .andExpect(content().contentType("application/json"))
            .andExpect(
                content().string(
                    containsString(
                        "Incorrect localization language: english"
                    )
                )
            )
    }

}

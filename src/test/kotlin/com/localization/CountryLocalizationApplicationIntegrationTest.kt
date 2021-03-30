package com.localization

import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/application-test.properties")
class CountryLocalizationApplicationIntegrationTest(@Autowired val mockMvc: MockMvc) {
    @Value("spring.datasource.url")
    val url = postgresqlContainer.jdbcUrl
    @Value("spring.datasource.username")
    val username = postgresqlContainer.username
    @Value("spring.datasource.password")
    val password = postgresqlContainer.password
    companion object {
        @JvmStatic
        @BeforeAll
        internal fun setUp() {
            postgresqlContainer.start()
        }
    }

    @Test
    fun checkResponseCodeWithCorrectData() {
        mockMvc.perform(get("/countries/AL?language=en"))
            .andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string(containsString("Albania")))
    }

    @Test
    fun checkResponseCodeWithIncorrectIso() {
        mockMvc.perform(get("/countries/OOO?language=en"))
            .andExpect(status().isBadRequest)
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string(containsString("Incorrect iso code of country: OOO")))

    }

    @Test
    fun checkResponseDataTypeWithIncorrectLanguage() {
        mockMvc.perform(get("/countries/AF?language=english"))
            .andExpect(status().isBadRequest)
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string(containsString("Incorrect localization language: english")))
    }

}

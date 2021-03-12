package com.localization.controller

import com.localization.exception.DataProcessingException
import com.localization.model.Country
import com.localization.model.dto.CountryResponseDto
import com.localization.service.CountryService
import com.localization.service.mapper.CountryMapToDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CountryController::class)
internal class CountryControllerTest {
    @Autowired
    val mockMvc: MockMvc? = null

    @MockBean
    val countryService: CountryService? = null

    @MockBean
    val countryMapToDto: CountryMapToDto? = null

    @BeforeEach
    internal fun setUp() {
        `when`(countryService?.getLocalizationByLanguageAndIso("IN", "en"))
            .thenReturn(Country(1, "India"))
        `when`(countryMapToDto?.mapToDto(Country(1, "India"))).thenReturn(
            CountryResponseDto("India")
        )
        `when`(
            countryService?.getLocalizationByLanguageAndIso(
                "INN",
                "en"
            )
        ).thenThrow(DataProcessingException::class.java)
        `when`(
            countryService?.getLocalizationByLanguageAndIso(
                "IN",
                "in"
            )
        ).thenThrow(DataProcessingException::class.java)
    }

    @Test
    fun getNameOfCountryCorrect() {
        mockMvc?.perform(get("/countries/IN?language=en"))?.andExpect(status().isOk)
    }

    @Test
    fun getNameOfCountryIncorrectIso() {
        mockMvc?.perform(get("/countries/INN?language=en"))?.andExpect(status().isNotFound)
    }

    @Test
    fun getNameOfCountryIncorrectLanguage() {

        mockMvc?.perform(get("/countries/IN?language=in"))?.andExpect(status().isNotFound)
    }
}

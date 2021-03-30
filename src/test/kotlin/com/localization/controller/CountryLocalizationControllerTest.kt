package com.localization.controller

import com.localization.exception.IncorrectInputDataException
import com.localization.model.Country
import com.localization.repository.LanguageRepository
import com.localization.service.CountryService
import com.localization.service.mapper.CountryMapToDto
import com.localization.service.mapper.impl.CountryMapToDtoImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class CountryLocalizationControllerTest {
    private val countryService: CountryService = mock()
    private val languageRepository: LanguageRepository = mock()
    private val countryMapToDto: CountryMapToDto = CountryMapToDtoImpl()
    private val mockMvc: MockMvc =
        MockMvcBuilders.standaloneSetup(CountryController(countryService, countryMapToDto)).build()

    @BeforeEach
    internal fun setUp() {
        reset(countryService, languageRepository)
    }

    @Test
    fun getNameOfCountryCorrect() {
        whenever(countryService.getLocalizationByLanguageAndIso("IN", "en"))
            .thenReturn(Country(1, "IN", "India"))
        mockMvc.perform(get("/countries/IN?language=en")).andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(
                MockMvcResultMatchers.content().string(Matchers.containsString("India"))
            )
    }

    @Test
    fun getNameOfCountryIncorrectIso() {
        whenever(
            countryService.getLocalizationByLanguageAndIso(
                "INN",
                "en"
            )
        ).thenThrow(IncorrectInputDataException("Incorrect iso code of country: INN"))
        mockMvc.perform(get("/countries/INN?language=en")).andExpect(status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(
                MockMvcResultMatchers.content().string(Matchers.containsString("Incorrect iso code of country: INN"))
            )
    }

    @Test
    fun getNameOfCountryIncorrectLanguage() {
        whenever(
            countryService.getLocalizationByLanguageAndIso(
                "IN",
                "in"
            )
        ).thenThrow(IncorrectInputDataException("Incorrect localization language: in"))
        mockMvc.perform(get("/countries/IN?language=in")).andExpect(status().isBadRequest)
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(
                MockMvcResultMatchers.content()
                    .string(Matchers.containsString("Incorrect localization language: in"))
            )
    }
}

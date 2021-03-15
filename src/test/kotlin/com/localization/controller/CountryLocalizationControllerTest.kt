package com.localization.controller

import com.localization.exception.IncorrectInputDataException
import com.localization.model.CountryLocalization
import com.localization.repository.IsoCodeRepository
import com.localization.repository.LanguageRepository
import com.localization.service.CountryService
import com.localization.service.mapper.CountryMapToDto
import com.localization.service.mapper.impl.CountryMapToDtoImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class CountryLocalizationControllerTest {
    private val countryService: CountryService = mock(CountryService::class.java)
    private val languageRepository = mock(LanguageRepository::class.java)
    private val isoCodeRepository = mock(IsoCodeRepository::class.java)
    private val countryMapToDto: CountryMapToDto = CountryMapToDtoImpl()
    private val mockMvc: MockMvc =
        MockMvcBuilders.standaloneSetup(CountryController(countryService, countryMapToDto)).build()

    @BeforeEach
    internal fun setUp() {
        reset(countryService, languageRepository, isoCodeRepository)
    }

    @Test
    fun getNameOfCountryCorrect() {
        `when`(countryService.getLocalizationByLanguageAndIso("IN", "en"))
            .thenReturn(CountryLocalization(1, "India", null, null))
        mockMvc.perform(get("/countries/IN?language=en")).andExpect(status().isOk)
    }

    @Test
    fun getNameOfCountryIncorrectIso() {
        `when`(
            countryService.getLocalizationByLanguageAndIso(
                "INN",
                "en"
            )
        ).thenThrow(IncorrectInputDataException::class.java)
        mockMvc.perform(get("/countries/INN?language=en")).andExpect(status().isBadRequest)
    }

    @Test
    fun getNameOfCountryIncorrectLanguage() {
        `when`(
            countryService.getLocalizationByLanguageAndIso(
                "IN",
                "in"
            )
        ).thenThrow(IncorrectInputDataException::class.java)
        mockMvc.perform(get("/countries/IN?language=in")).andExpect(status().isBadRequest)
    }
}

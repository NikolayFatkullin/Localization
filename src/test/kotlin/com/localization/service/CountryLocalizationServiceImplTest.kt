package com.localization.service

import com.localization.exception.IncorrectInputDataException
import com.localization.model.Country
import com.localization.repository.CountryRepository
import com.localization.repository.LanguageRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class CountryLocalizationServiceImplTest {
    private val countryRepository: CountryRepository = mock()
    private val languageRepository: LanguageRepository = mock()
    private val countryService: CountryService =
        CountryServiceImpl(countryRepository, languageRepository)


    @BeforeEach
    internal fun setUp() {
        reset(languageRepository, countryRepository)
    }

    @Test
    fun getLocalizationByLanguageAndIso() {
        whenever(countryRepository.existsByIso("KM")).thenReturn(true)
        whenever(languageRepository.existsByLanguage("ru")).thenReturn(true)
        whenever(
            countryRepository.getLocalizedName(
                "KM",
                "ru"
            )
        ).thenReturn(Country( 1,"KM","Коморы"))
        val firstExpectedCountryName = "Коморы"
        val firstActualCountryName =
            countryService.getLocalizationByLanguageAndIso("KM", "ru").name
        assertEquals(
            firstExpectedCountryName,
            firstActualCountryName,
            "Incorrect value returned, should be: $firstExpectedCountryName"
        )
    }

    @Test
    fun checkForIncorrectIso() {
        whenever(
            countryRepository.existsByIso(
                "auu"
            )
        )
            .thenReturn(false)
        assertThrows(IncorrectInputDataException::class.java) {
            countryService.getLocalizationByLanguageAndIso(
                "auu",
                "ru"
            )
        }
    }

    @Test
    fun checkForIncorrectLanguage() {
        whenever(
            languageRepository.existsByLanguage(
                "in"
            )
        )
            .thenReturn(false)
        assertThrows(IncorrectInputDataException::class.java) {
            countryService.getLocalizationByLanguageAndIso(
                "IN",
                "in"
            )
        }
    }
}

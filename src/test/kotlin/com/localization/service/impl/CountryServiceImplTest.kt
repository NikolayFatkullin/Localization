package com.localization.service.impl

import com.localization.service.CountryService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.RuntimeException

@SpringBootTest
internal class CountryServiceImplTest(
    @Autowired
    val countryService: CountryService
) {

    @Test
    fun getLocalizationByLanguageAndIso() {
        val firstExpectedCountryName = "Коморы"
        val firstActualCountryName =
            countryService.getLocalizationByLanguageAndIso("KM", "ru").name
        val secondExpectedCountryName = "Гернсі"
        val secondActualCountryName =
            countryService.getLocalizationByLanguageAndIso("GG", "ua").name
        assertEquals(
            firstExpectedCountryName,
            firstActualCountryName,
            "Incorrect value returned, should be: $firstExpectedCountryName"
        )
        assertEquals(
            secondExpectedCountryName,
            secondActualCountryName,
            "Incorrect value returned, should be: $firstExpectedCountryName"
        )
    }

    @Test
    fun checkForIncorrectData() {
        assertThrows(RuntimeException::class.java) {
            countryService.getLocalizationByLanguageAndIso(
                "au",
                "IN"
            )
        }
        assertThrows(RuntimeException::class.java) {
            countryService.getLocalizationByLanguageAndIso(
                "ua",
                "INSSS"
            )
        }
    }
}
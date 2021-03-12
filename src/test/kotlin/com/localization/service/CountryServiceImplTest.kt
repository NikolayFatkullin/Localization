package com.localization.service

import com.localization.exception.DataProcessingException
import com.localization.model.Country
import com.localization.repository.CountryRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.TestPropertySource


@SpringBootTest
@TestPropertySource(
        locations = ["classpath:application-test.properties"]
)
internal class CountryServiceImplTest {
    @Autowired
    val countryService: CountryService? = null

    @MockBean
    val countryRepository: CountryRepository? = null

    @BeforeEach
    internal fun setUp() {
        `when`(
                countryRepository?.getByIsoCodeAndLanguage(
                        "KM",
                        "ru"
                )
        ).thenReturn(Country(12, "Коморы"))
        `when`(
                countryRepository?.getByIsoCodeAndLanguage(
                        "GG",
                        "ua"
                )
        ).thenReturn(Country(15, "Гернсі"))
        `when`(
                countryRepository?.getByIsoCodeAndLanguage(
                        "auu",
                        "ru"
                )
        ).thenThrow(DataProcessingException::class.java)
        `when`(
                countryRepository?.getByIsoCodeAndLanguage(
                        "IN",
                        "in"
                )
        ).thenThrow(DataProcessingException::class.java)
        `when`(
                countryRepository?.getByIsoCodeAndLanguage(
                        "ASD", "EN"
                )
        )
                .thenReturn(null)
    }

    @Test
    fun getLocalizationByLanguageAndIso() {
        val firstExpectedCountryName = "Коморы"
        val firstActualCountryName =
                countryService?.getLocalizationByLanguageAndIso("KM", "ru")?.name
        val secondExpectedCountryName = "Гернсі"
        val secondActualCountryName =
                countryService?.getLocalizationByLanguageAndIso("GG", "ua")?.name
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
    fun checkForIncorrectIso() {
        assertThrows(DataProcessingException::class.java) {
            countryService?.getLocalizationByLanguageAndIso(
                    "auu",
                    "ru"
            )
        }
    }

    @Test
    fun checkForIncorrectLanguage() {
        assertThrows(DataProcessingException::class.java) {
            countryService?.getLocalizationByLanguageAndIso(
                    "IN",
                    "in"
            )
        }
    }

    @Test
    internal fun checkForExceptionThrowing() {
        val exception = assertThrows(DataProcessingException::class.java) {
            countryService?.getLocalizationByLanguageAndIso("ASD", "EN")
        }
        assertEquals("Can't get name of country by iso: ASD and language: EN", exception.message)
    }
}

//package com.localization.service
//
//import com.localization.exception.IncorrectInputDataException
//import com.localization.repository.CountryRepository
//import com.localization.repository.LanguageRepository
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Assertions.assertThrows
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito.*
//
//internal class CountryLocalizationServiceImplTest {
//    private val countryRepository: CountryRepository = mock(CountryRepository::class.java)
//    private val languageRepository: LanguageRepository = mock(LanguageRepository::class.java)
//    private val isoCodeRepository: CountryRepository = mock(CountryRepository::class.java)
//    private val countryService: CountryService =
//        CountryServiceImpl(countryRepository, isoCodeRepository, languageRepository)
//
//
//    @BeforeEach
//    internal fun setUp() {
//        reset(languageRepository, isoCodeRepository, countryRepository)
//    }
//
//    @Test
//    fun getLocalizationByLanguageAndIso() {
//        `when`(isoCodeRepository.existsByIso("KM")).thenReturn(true)
//        `when`(languageRepository.existsByLanguage("ru")).thenReturn(true)
//        `when`(
//            countryRepository.getByIsoCodeAndLanguage(
//                "KM",
//                "ru"
//            )
//        ).thenReturn(CountryLocalization(12, "Коморы", null, null))
//        val firstExpectedCountryName = "Коморы"
//        val firstActualCountryName =
//            countryService.getLocalizationByLanguageAndIso("KM", "ru").name
//        assertEquals(
//            firstExpectedCountryName,
//            firstActualCountryName,
//            "Incorrect value returned, should be: $firstExpectedCountryName"
//        )
//    }
//
//    @Test
//    fun checkForIncorrectIso() {
//        `when`(
//            isoCodeRepository.existsByIso(
//                "auu"
//            )
//        )
//            .thenReturn(false)
//        assertThrows(IncorrectInputDataException::class.java) {
//            countryService.getLocalizationByLanguageAndIso(
//                "auu",
//                "ru"
//            )
//        }
//    }
//
//    @Test
//    fun checkForIncorrectLanguage() {
//        `when`(
//            languageRepository.existsByLanguage(
//                "in"
//            )
//        )
//            .thenReturn(false)
//        assertThrows(IncorrectInputDataException::class.java) {
//            countryService.getLocalizationByLanguageAndIso(
//                "IN",
//                "in"
//            )
//        }
//    }
//}

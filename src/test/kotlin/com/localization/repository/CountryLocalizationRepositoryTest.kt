//package com.localization.repository
//
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.test.context.TestPropertySource
//
//@DataJpaTest
//@TestPropertySource(locations = ["classpath:application-test.properties"])
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//internal class CountryLocalizationRepositoryTest {
//    @Autowired
//    val countryRepository: CountryRepository? = null
//
//    @Test
//    internal fun testForCorrectData() {
//        val firstCountryName = "Afghanistan"
//        val firstCountry = countryRepository?.getByIsoCodeAndLanguage("AF", "en")
//        assertEquals(firstCountryName, firstCountry?.name)
//    }
//
//    @Test
//    internal fun testForIncorrectIso() {
//        val firstCountry = countryRepository?.getByIsoCodeAndLanguage("AFF", "en")
//        assertEquals(null, firstCountry?.name)
//    }
//
//    @Test
//    internal fun testForIncorrectLanguage() {
//        val firstCountry = countryRepository?.getByIsoCodeAndLanguage("AF", "english")
//        assertEquals(null, firstCountry?.name)
//    }
//}

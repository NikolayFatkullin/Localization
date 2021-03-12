package com.localization.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["classpath:application-test.properties"])
internal class CountryRepositoryTest {
    @field:Autowired
    val countryRepository: CountryRepository? = null

    @Test
    internal fun testForCorrectData() {
        val firstCountryName = "Afghanistan"
        val secondCountryName = "Albania"
        val thirdCountryName = "Антарктика"
        val firstCountry = countryRepository?.getByIsoCodeAndLanguage("AF", "en")
        val secondCountry = countryRepository?.getByIsoCodeAndLanguage("AL", "en")
        val thirdCountry = countryRepository?.getByIsoCodeAndLanguage("AQ", "ru")
        assertEquals(firstCountryName, firstCountry?.name)
        assertEquals(secondCountryName, secondCountry?.name)
        assertEquals(thirdCountryName, thirdCountry?.name)
    }
}

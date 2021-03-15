package com.localization.repository

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["classpath:application-test.properties"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CountryRepositoryTest {
    @Autowired
    val isoCodeRepository: IsoCodeRepository? = null

    @Test
    internal fun testForCorrectData() {
        assertTrue(isoCodeRepository!!.existsByIso("AF"))
    }

    @Test
    internal fun testForIncorrectIso() {
        assertFalse(isoCodeRepository!!.existsByIso("AFF"))
    }

}
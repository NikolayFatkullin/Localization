package com.localization.repository

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@TestPropertySource(locations = ["classpath:application-test.properties"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LanguageRepositoryTest {
    @Autowired
    val languageRepository: LanguageRepository? = null

    @Test
    internal fun testForCorrectData() {
        Assertions.assertTrue(languageRepository!!.existsByLanguage("en"))
    }

    @Test
    internal fun testForIncorrectIso() {
        Assertions.assertFalse(languageRepository!!.existsByLanguage("english"))
    }

}
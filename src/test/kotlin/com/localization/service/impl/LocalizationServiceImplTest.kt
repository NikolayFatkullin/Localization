package com.localization.service.impl

import com.localization.model.Localization
import com.localization.service.LocalizationService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.RuntimeException

@SpringBootTest
internal class LocalizationServiceImplTest(
    @Autowired
    val localizationService: LocalizationService
) {

    @Test
    fun getLocalizationByLanguageAndIso() {
        val firstExpectedLocalization = Localization()
        firstExpectedLocalization.id = 49
        firstExpectedLocalization.iso = "KM"
        firstExpectedLocalization.localized = "Коморы"
        val firstActualLocalization =
            localizationService.getLocalizationByLanguageAndIso("ru", "KM")
        val secondExpectedLocalization = Localization()
        secondExpectedLocalization.id = 92
        secondExpectedLocalization.iso = "GG"
        secondExpectedLocalization.localized = "Гернсі"
        val secondActualLocalization =
            localizationService.getLocalizationByLanguageAndIso("ua", "GG")
        assertEquals(
            firstExpectedLocalization.localized,
            firstActualLocalization.localized,
            "Incorrect value returned, should be: ${firstExpectedLocalization.localized}"
        )
        assertEquals(
            secondExpectedLocalization.localized,
            secondActualLocalization.localized,
            "Incorrect value returned, should be: ${firstExpectedLocalization.localized}"
        )
    }

    @Test
    fun checkForIncorrectData() {
        assertThrows(RuntimeException::class.java) {
            localizationService.getLocalizationByLanguageAndIso(
                "au",
                "IN"
            )
        }
        assertThrows(RuntimeException::class.java) {
            localizationService.getLocalizationByLanguageAndIso(
                "ua",
                "INSSS"
            )
        }
    }
}
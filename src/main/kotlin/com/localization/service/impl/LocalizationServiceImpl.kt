package com.localization.service.impl

import com.localization.model.Localization
import com.localization.repository.LocalizationRepository
import com.localization.service.LocalizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class LocalizationServiceImpl(@field:Autowired val localizationRepository: LocalizationRepository) :
    LocalizationService {
    override fun getLocalizationByLanguageAndIso(language: String, iso: String): Localization {
        val localization = localizationRepository.findByLanguageAndIso(language, iso)
        if (localization.localized.isEmpty())
            throw RuntimeException("Can't get name of country by iso: $iso and language: $language")
        else return localization
    }
}
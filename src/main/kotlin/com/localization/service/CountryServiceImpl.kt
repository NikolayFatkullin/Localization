package com.localization.service

import com.localization.exception.DataNotFoundException
import com.localization.exception.IncorrectInputDataException
import com.localization.model.Country
import com.localization.repository.CountryRepository
import com.localization.repository.LanguageRepository
import org.springframework.stereotype.Service

@Service
class CountryServiceImpl(
        val countryRepository: CountryRepository,
        val languageRepository: LanguageRepository
) :
    CountryService {
    override fun getLocalizationByLanguageAndIso(isoCode: String, language: String): Country {
        if (!countryRepository.existsByIso(isoCode)) {
            throw IncorrectInputDataException("Incorrect iso code of country: $isoCode")
        }
        if (!languageRepository.existsByLanguage(language)) {
            throw IncorrectInputDataException("Incorrect localization language: $language")
        }
        return countryRepository.getLocalizedName(isoCode, language)
            ?: throw DataNotFoundException("Can't get name of country by iso: $isoCode and language: $language")
    }
}

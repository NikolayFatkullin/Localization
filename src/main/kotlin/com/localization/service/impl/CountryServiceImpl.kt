package com.localization.service.impl

import com.localization.exception.DataProcessingException
import com.localization.model.Country
import com.localization.repository.CountryRepository
import com.localization.service.CountryService
import org.springframework.stereotype.Service

@Service
class CountryServiceImpl(val countryRepository: CountryRepository) :
    CountryService {
    override fun getLocalizationByLanguageAndIso(isoCode: String, language: String): Country {
        return countryRepository.getByIsoCodeAndLanguage(isoCode, language)
            ?: throw DataProcessingException(
                "Can't get name of country by iso: $isoCode and language: $language"
            )
    }
}

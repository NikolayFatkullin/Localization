package com.localization.service

import com.localization.exception.DataNotFoundException
import com.localization.exception.IncorrectInputDataException
import com.localization.model.CountryLocalization
import com.localization.repository.CountryRepository
import com.localization.repository.IsoCodeRepository
import com.localization.repository.LanguageRepository
import org.springframework.stereotype.Service

@Service
class CountryServiceImpl(
    val countryRepository: CountryRepository,
    val isoCodeRepository: IsoCodeRepository,
    val languageRepository: LanguageRepository
) :
    CountryService {
    override fun getLocalizationByLanguageAndIso(isoCode: String, language: String): CountryLocalization {
        if (!isoCodeRepository.existsByIso(isoCode)) {
            throw IncorrectInputDataException("Incorrect iso code of country: $isoCode")
        }
        if (!languageRepository.existsByLanguage(language)) {
            throw IncorrectInputDataException("Incorrect localization language: $language")
        }
        return countryRepository.getByIsoCodeAndLanguage(isoCode, language)
            ?: throw DataNotFoundException("Can't get name of country by iso: $isoCode and language: $language")
    }
}

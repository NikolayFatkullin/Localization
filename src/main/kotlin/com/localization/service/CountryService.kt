package com.localization.service

import com.localization.model.CountryLocalization

interface CountryService {
    fun getLocalizationByLanguageAndIso(isoCode: String, language: String): CountryLocalization
}
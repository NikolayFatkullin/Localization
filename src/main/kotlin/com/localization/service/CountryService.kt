package com.localization.service

import com.localization.model.Country

interface CountryService {
    fun getLocalizationByLanguageAndIso(isoCode: String, language: String): Country
}
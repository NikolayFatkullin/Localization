package com.localization.service

import com.localization.model.EntityResponseDto

interface CountryService {
    fun getLocalizationByLanguageAndIso(isoCode: String, language: String): EntityResponseDto
}
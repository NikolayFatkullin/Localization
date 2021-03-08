package com.localization.service

import com.localization.model.Localization

interface LocalizationService {
    fun getLocalizationByLanguageAndIso(language: String, iso: String): Localization
}
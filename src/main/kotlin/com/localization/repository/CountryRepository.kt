package com.localization.repository

import com.localization.model.EntityResponseDto

interface CountryRepository {
    fun existsByIso(isoCode: String): Boolean
    fun getLocalizedName(isoCode: String, language: String): EntityResponseDto?
}
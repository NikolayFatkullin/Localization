package com.localization.repository

import com.localization.model.Country

interface CountryRepository {
    fun existsByIso(isoCode: String): Boolean
    fun getLocalizedName(isoCode: String, language: String): Country?
}
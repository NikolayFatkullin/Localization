package com.localization.service.mapper

import com.localization.model.CountryResponse
import com.localization.model.Country

interface CountryMapToDto {
    fun mapToDto(country: Country): CountryResponse
}

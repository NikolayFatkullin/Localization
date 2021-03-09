package com.localization.service.mapper

import com.localization.model.Country
import com.localization.model.dto.CountryResponseDto

interface CountryMapToDto {
    fun mapToDto(country: Country): CountryResponseDto
}
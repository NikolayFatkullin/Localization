package com.localization.service.mapper

import com.localization.model.CountryResponseDto
import com.localization.model.EntityResponseDto

interface CountryMapToDto {
    fun mapToDto(countryLocalization: EntityResponseDto): CountryResponseDto
}

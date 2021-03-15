package com.localization.service.mapper

import com.localization.model.CountryLocalization
import com.localization.model.dto.CountryResponseDto

interface CountryMapToDto {
    fun mapToDto(countryLocalization: CountryLocalization): CountryResponseDto
}

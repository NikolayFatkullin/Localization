package com.localization.service.mapper.impl

import com.localization.model.CountryResponseDto
import com.localization.model.EntityResponseDto
import com.localization.service.mapper.CountryMapToDto
import org.springframework.stereotype.Component

@Component
class CountryMapToDtoImpl : CountryMapToDto {
    override fun mapToDto(countryLocalization: EntityResponseDto): CountryResponseDto = CountryResponseDto(countryLocalization.localizedName)
}

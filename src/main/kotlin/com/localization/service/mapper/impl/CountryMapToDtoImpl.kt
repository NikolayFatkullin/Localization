package com.localization.service.mapper.impl

import com.localization.model.Country
import com.localization.model.dto.CountryResponseDto
import com.localization.service.mapper.CountryMapToDto
import org.springframework.stereotype.Component

@Component
class CountryMapToDtoImpl : CountryMapToDto {
    override fun mapToDto(country: Country): CountryResponseDto = CountryResponseDto(country.name)
}

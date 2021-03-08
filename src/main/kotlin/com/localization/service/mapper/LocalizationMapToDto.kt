package com.localization.service.mapper

import com.localization.model.Localization
import com.localization.model.dto.LocalizationResponseDto

interface LocalizationMapToDto {
    fun mapToDto(localization: Localization): LocalizationResponseDto
}
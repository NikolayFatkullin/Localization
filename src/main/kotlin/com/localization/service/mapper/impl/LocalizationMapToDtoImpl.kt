package com.localization.service.mapper.impl

import com.localization.model.Localization
import com.localization.model.dto.LocalizationResponseDto
import com.localization.service.mapper.LocalizationMapToDto
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class LocalizationMapToDtoImpl : LocalizationMapToDto {
    override fun mapToDto(localization: Localization): LocalizationResponseDto {
        val localizationResponseDto = LocalizationResponseDto()
        localizationResponseDto.localizedName = localization.localized
        return localizationResponseDto
    }
}
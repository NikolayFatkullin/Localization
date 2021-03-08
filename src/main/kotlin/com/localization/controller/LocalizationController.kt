package com.localization.controller

import com.localization.model.dto.LocalizationResponseDto
import com.localization.service.LocalizationService
import com.localization.service.mapper.LocalizationMapToDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LocalizationController(
    @Autowired val localizationService: LocalizationService,
    @Autowired val localizationMapToDto: LocalizationMapToDto
) {

    @GetMapping("/country-name")
    fun getNameOfCountry(
        @RequestParam language: String,
        @RequestParam iso: String
    ): LocalizationResponseDto {
        val localizationByLanguageAndIso =
            localizationService.getLocalizationByLanguageAndIso(language, iso)
        return localizationMapToDto.mapToDto(localizationByLanguageAndIso)
    }
}
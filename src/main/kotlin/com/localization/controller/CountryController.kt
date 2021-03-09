package com.localization.controller

import com.localization.model.dto.CountryResponseDto
import com.localization.service.CountryService
import com.localization.service.mapper.CountryMapToDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CountryController(
    val countryService: CountryService,
    val countryMapToDto: CountryMapToDto
) {

    @GetMapping("/countries/{isoCode}")
    fun getNameOfCountry(
        @PathVariable isoCode: String, @RequestParam language: String,
    ): CountryResponseDto {
        val localizationByLanguageAndIso =
            countryService.getLocalizationByLanguageAndIso(isoCode, language)
        return countryMapToDto.mapToDto(localizationByLanguageAndIso)
    }
}
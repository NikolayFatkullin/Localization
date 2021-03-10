package com.localization.controller

import com.localization.exception.DataProcessingException
import com.localization.model.dto.CountryResponseDto
import com.localization.service.CountryService
import com.localization.service.mapper.CountryMapToDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest


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

    @ExceptionHandler(value = [DataProcessingException::class])
    fun handleConflict(
        ex: RuntimeException?, request: WebRequest?
    ): ResponseEntity<Any?>? {
        return ResponseEntity(
            ex?.message, HttpStatus.NOT_FOUND
        )
    }
}
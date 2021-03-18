package com.localization.controller

import com.localization.exception.DataNotFoundException
import com.localization.exception.IncorrectInputDataException
import com.localization.model.CountryResponse
import com.localization.service.CountryService
import com.localization.service.mapper.CountryMapToDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class CountryController(
    val countryService: CountryService,
    val countryMapToDto: CountryMapToDto
) {

    @GetMapping("/countries/{isoCode}")
    fun getNameOfCountry(
        @PathVariable isoCode: String, @RequestParam language: String,
    ): CountryResponse {
        val localizationByLanguageAndIso =
            countryService.getLocalizationByLanguageAndIso(isoCode, language)
        return countryMapToDto.mapToDto(localizationByLanguageAndIso)
    }

    @ExceptionHandler(value = [DataNotFoundException::class])
    fun handleNotFound(
        ex: DataNotFoundException
    ): ResponseEntity<Any?> {
        val body = HashMap<String, String>()
        body["message"] = ex.message.toString()
        body["status"] = HttpStatus.NOT_FOUND.toString()
        return ResponseEntity(
            body, HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(value = [IncorrectInputDataException::class])
    fun handleIncorrectInput(
        ex: IncorrectInputDataException
    ): ResponseEntity<Any> {
        val body = HashMap<String, String>()
        body["message"] = ex.message.toString()
        body["status"] = HttpStatus.BAD_REQUEST.toString()
        return ResponseEntity(
            body, HttpStatus.BAD_REQUEST
        )
    }
}

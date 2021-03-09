package com.localization.repository

import com.localization.model.Country
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CountryRepository : JpaRepository<Country, Int> {
    @Query(
        value = "SELECT c.id, c.name FROM country AS c LEFT JOIN country_codes AS cc ON cc.id = c.iso_id " +
                "LEFT JOIN language AS l ON l.id = c.language_id " +
                "WHERE l.language = :language AND cc.iso = :isoCode",
        nativeQuery = true
    )
    fun getByIsoCodeAndLanguage(
        @Param("isoCode") isoCode: String,
        @Param("language") language: String
    ): Country?
}

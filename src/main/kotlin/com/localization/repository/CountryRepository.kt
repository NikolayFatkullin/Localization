package com.localization.repository

import com.localization.model.CountryLocalization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CountryRepository : JpaRepository<CountryLocalization, Int> {
    @Query(
        value = "FROM CountryLocalization c LEFT JOIN FETCH c.language LEFT JOIN FETCH c.country " +
                "WHERE c.language.language = :language AND c.country.iso = :isoCode"
    )
    fun getByIsoCodeAndLanguage(
        @Param("isoCode") isoCode: String,
        @Param("language") language: String
    ): CountryLocalization?
}

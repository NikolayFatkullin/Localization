package com.localization.repository

import com.localization.model.Country
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CountryRepository : JpaRepository<Country, Int> {
    @Query(
        value = "FROM Country c LEFT JOIN FETCH c.language LEFT JOIN FETCH c.isoCode " +
                "WHERE c.language.language = :language AND c.isoCode.iso = :isoCode"
    )
    fun getByIsoCodeAndLanguage(
        @Param("isoCode") isoCode: String,
        @Param("language") language: String
    ): Country?
}

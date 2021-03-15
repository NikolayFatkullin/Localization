package com.localization.repository

import com.localization.model.Country
import org.springframework.data.jpa.repository.JpaRepository

interface IsoCodeRepository: JpaRepository<Country, Long> {
    fun existsByIso(isoCode: String): Boolean
}
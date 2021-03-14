package com.localization.repository

import com.localization.model.IsoCode
import org.springframework.data.jpa.repository.JpaRepository

interface IsoCodeRepository: JpaRepository<IsoCode, Long> {
    fun existsByIso(isoCode: String): Boolean
}
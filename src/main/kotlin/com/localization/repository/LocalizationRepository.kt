package com.localization.repository

import com.localization.model.Localization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface LocalizationRepository : JpaRepository<Localization, Int> {

    @Query(
        value = "SELECT id, iso, localized -> :language AS localized From localization "
                + "WHERE iso = :iso",
        nativeQuery = true
    )
    fun findByLanguageAndIso(
        @Param("language") language: String,
        @Param("iso") iso: String
    ): Localization
}

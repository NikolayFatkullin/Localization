package com.localization.repository

import com.localization.model.Language
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageRepository: JpaRepository<Language, Long> {
    fun existsByLanguage(language : String): Boolean
}
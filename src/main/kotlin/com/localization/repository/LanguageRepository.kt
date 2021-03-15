package com.localization.repository

interface LanguageRepository {
    fun existsByLanguage(language : String): Boolean
}
package com.localization.repository.impl

import com.localization.repository.LanguageRepository
import com.localization.repository.impl.CountryRepositoryImpl.Companion
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class LanguageRepositoryImpl(val jdbcTemplate: NamedParameterJdbcTemplate) : LanguageRepository {
    companion object {
        private const val QUERY_FOR_EXISTS = "SELECT EXISTS(SELECT * FROM language l WHERE l.language = :language)"
    }

    override fun existsByLanguage(language: String): Boolean =
        jdbcTemplate.queryForObject(QUERY_FOR_EXISTS, mapOf("language" to language), Boolean::class.java)!!
}
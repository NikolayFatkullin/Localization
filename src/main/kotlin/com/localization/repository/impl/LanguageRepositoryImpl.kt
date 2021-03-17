package com.localization.repository.impl

import com.localization.repository.LanguageRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class LanguageRepositoryImpl(val jdbcTemplate: NamedParameterJdbcTemplate) : LanguageRepository {
    companion object {
        const val QUERY_FOR_EXISTS = "SELECT language FROM language WHERE language = :language"
    }

    override fun existsByLanguage(language: String): Boolean =
        jdbcTemplate.query(
            QUERY_FOR_EXISTS,
            MapSqlParameterSource().addValue("language", language)
        ) { rs: ResultSet, _: Int ->
            rs
        }.size > 0
}
package com.localization.repository.impl

import com.localization.model.EntityResponseDto
import com.localization.repository.CountryRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class CountryRepositoryImpl(private val jdbcTemplate: NamedParameterJdbcTemplate) : CountryRepository {
    companion object {
        const val QUERY_FOR_EXISTS = "SELECT iso FROM country c WHERE iso = :isoCode"
        const val QUERY_FOR_GET = "SELECT c.iso AS isoCode, cl.name AS localizedName FROM country_localization cl " +
                "INNER JOIN country c ON c.id = cl.country_id " +
                "INNER JOIN language l ON l.id = cl.language_id " +
                "WHERE c.iso = :isoCode AND l.language = :language"
    }

    override fun existsByIso(isoCode: String): Boolean =
        jdbcTemplate.query(
            QUERY_FOR_EXISTS,
            MapSqlParameterSource().addValue("isoCode", isoCode)
        ) { rs: ResultSet, _: Int ->
            rs
        }.size > 0


    override fun getLocalizedName(isoCode: String, language: String): EntityResponseDto? {
        val sqlParameterSource = MapSqlParameterSource().addValue("isoCode", isoCode).addValue("language", language)
        return jdbcTemplate.queryForObject(
            QUERY_FOR_GET, sqlParameterSource
        ) { rs: ResultSet, _: Int ->
            EntityResponseDto(
                rs.getString("isoCode"),
                rs.getString("localizedName")
            )
        }
    }
}

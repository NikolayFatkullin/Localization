package com.localization.repository.impl

import com.localization.model.Country
import com.localization.repository.CountryRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class CountryRepositoryImpl(private val jdbcTemplate: NamedParameterJdbcTemplate) : CountryRepository {
    companion object {
        private const val QUERY_FOR_EXISTS = "SELECT EXISTS(SELECT * FROM country WHERE country.iso = :isoCode)"
        private const val QUERY_FOR_GET = """
            SELECT c.id, c.iso AS isoCode, cl.name AS localizedName FROM country_localization cl 
            INNER JOIN country c ON c.id = cl.country_id 
            INNER JOIN language l ON l.id = cl.language_id 
            WHERE c.iso = :isoCode AND l.language = :language
            """
    }

    override fun existsByIso(isoCode: String): Boolean =
        jdbcTemplate.queryForObject(QUERY_FOR_EXISTS, mapOf("isoCode" to isoCode), Boolean::class.java)!!


    override fun getLocalizedName(isoCode: String, language: String): Country? = jdbcTemplate.queryForObject(
        QUERY_FOR_GET,
        mapOf(
            "isoCode" to isoCode,
            "language" to language
        )
    ) { rs: ResultSet, _: Int ->
        Country(
            rs.getLong("id"),
            rs.getString("isoCode"),
            rs.getString("localizedName")
        )
    }

}

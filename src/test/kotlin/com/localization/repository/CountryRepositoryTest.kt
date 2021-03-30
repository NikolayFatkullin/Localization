package com.localization.repository

import com.localization.repository.impl.CountryRepositoryImpl
import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

internal class CountryRepositoryTest {
    companion object {
        private val datasource = getDataSource()
        private val jdbcTemplate = NamedParameterJdbcTemplate(datasource)
        private val countryRepository: CountryRepository = CountryRepositoryImpl(jdbcTemplate)

        @BeforeAll
        @JvmStatic
        internal fun setUp() {
            val database =
                DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(datasource.connection))
            val liquibase = Liquibase("db/changelog/db-migration.xml", ClassLoaderResourceAccessor(), database)
            liquibase.update(Contexts("test"))
        }

    }

    @Test
    internal fun testForCorrectData() {
        val countryName = "Afghanistan"
        val country = countryRepository.getLocalizedName("AF", "en")
        assertEquals(countryName, country?.name)
    }

    @Test
    internal fun testForIncorrectIso() {
        assertThrows<EmptyResultDataAccessException> {
            countryRepository.getLocalizedName("AFF", "en")
        }
    }

    @Test
    internal fun testForIncorrectLanguage() {
        assertThrows<EmptyResultDataAccessException> {
            countryRepository.getLocalizedName("AF", "english")
        }
    }

    @Test
    internal fun testForExistCorrectIso() {
        assertTrue(countryRepository.existsByIso("AF"))
    }

    @Test
    internal fun testForExistIncorrectIso() {
        assertFalse(countryRepository.existsByIso("AFA"))
    }
}

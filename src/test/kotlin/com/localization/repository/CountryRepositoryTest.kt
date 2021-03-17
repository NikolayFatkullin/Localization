package com.localization.repository

import com.localization.repository.impl.CountryRepositoryImpl
import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.sql.DriverManager

internal class CountryRepositoryTest {
    private val datasource = RepositoryConfig().getDataSource()
    private val jdbcTemplate = NamedParameterJdbcTemplate(datasource)
    private val countryRepository: CountryRepository = CountryRepositoryImpl(jdbcTemplate)

    companion object {
        private val connection = DriverManager.getConnection("jdbc:hsqldb:mem:countries", "sa", "")

        @BeforeAll
        @JvmStatic
        internal fun setUp() {
            val database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(connection))
            val liquibase = Liquibase("db/changelog/db-migration.xml", ClassLoaderResourceAccessor(), database)
            liquibase.update(Contexts("test"))
        }

        @AfterAll
        @JvmStatic
        internal fun tearDown() {
            if (connection != null) {
                connection.rollback()
                connection.close()
            }
        }
    }

    @Test
    internal fun testForCorrectData() {
        val firstCountryName = "Afghanistan"
        val firstCountry = countryRepository.getLocalizedName("AF", "en")
        assertEquals(firstCountryName, firstCountry?.localizedName)
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

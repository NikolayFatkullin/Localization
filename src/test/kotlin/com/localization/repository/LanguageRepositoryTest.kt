package com.localization.repository

import com.localization.repository.impl.LanguageRepositoryImpl
import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.sql.DriverManager


class LanguageRepositoryTest {
    private val datasource = RepositoryConfig().getDataSource()
    private val jdbcTemplate = NamedParameterJdbcTemplate(datasource)
    private val languageRepository = LanguageRepositoryImpl(jdbcTemplate)
    companion object {
        private val connection = DriverManager.getConnection("jdbc:hsqldb:mem:countries", "sa", "")

        @BeforeAll
        @JvmStatic
        fun setUp() {
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
        assertTrue(languageRepository.existsByLanguage("en"))
    }

    @Test
    internal fun testForIncorrectIso() {
        assertFalse(languageRepository.existsByLanguage("english"))
    }


}
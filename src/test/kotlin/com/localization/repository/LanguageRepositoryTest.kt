package com.localization.repository

import com.localization.repository.impl.LanguageRepositoryImpl
import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate


class LanguageRepositoryTest {
    companion object {
        private val datasource = getDataSource()
        private val jdbcTemplate = NamedParameterJdbcTemplate(datasource)
        private val languageRepository: LanguageRepository = LanguageRepositoryImpl(jdbcTemplate)

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
        assertTrue(languageRepository.existsByLanguage("en"))
    }

    @Test
    internal fun testForIncorrectIso() {
        assertFalse(languageRepository.existsByLanguage("english"))
    }


}
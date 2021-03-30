package com.localization.repository

import com.localization.postgresqlContainer
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.testcontainers.containers.PostgreSQLContainer
import javax.sql.DataSource


fun getDataSource(): DataSource = HikariDataSource(hikariConfig())

fun hikariConfig(): HikariConfig {
    postgresqlContainer.start()
    return HikariConfig()
        .apply {
            jdbcUrl = postgresqlContainer.jdbcUrl
            username = postgresqlContainer.username
            password = postgresqlContainer.username
        }
}
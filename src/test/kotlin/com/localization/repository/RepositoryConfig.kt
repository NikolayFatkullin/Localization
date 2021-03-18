package com.localization.repository

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.jdbc.DataSourceBuilder
import javax.sql.DataSource

fun getDataSource(): DataSource = HikariDataSource(hikariConfig())

fun hikariConfig() = HikariConfig()
    .apply {
        jdbcUrl = "jdbc:h2:mem:countries"
        username = "sa"
        password = ""
    }
package com.localization.repository

import org.springframework.boot.jdbc.DataSourceBuilder
import javax.sql.DataSource

class RepositoryConfig {
    fun getDataSource() : DataSource {
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.hsqldb.jdbc.JDBCDriver")
        dataSourceBuilder.url("jdbc:hsqldb:mem:countries")
        dataSourceBuilder.username("sa")
        dataSourceBuilder.password("")
        return dataSourceBuilder.build()
    }
}

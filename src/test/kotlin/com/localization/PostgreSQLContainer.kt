package com.localization

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container

@Container
val postgresqlContainer = PostgreSQLContainer<Nothing>("postgres:9.6").apply {
    withDatabaseName("countries")
    withUsername("postgres")
    withPassword("postgres")
}

package com.localization

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LocalizationApplication

fun main(args: Array<String>) {
    runApplication<LocalizationApplication>(*args)
}

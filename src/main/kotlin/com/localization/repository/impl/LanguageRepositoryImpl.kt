package com.localization.repository.impl

import com.localization.repository.LanguageRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class LanguageRepositoryImpl(val entityManager: EntityManager) : LanguageRepository {
    override fun existsByLanguage(language: String): Boolean = entityManager
            .createNativeQuery("SELECT language FROM language WHERE language = ?").setParameter(1, language).resultList.size > 0
}
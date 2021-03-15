package com.localization.repository.impl

import com.localization.model.EntityResponseDto
import com.localization.repository.CountryRepository
import org.hibernate.Session
import org.omg.CORBA.Object
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Query
import javax.transaction.Transaction

@Repository
class CountryRepositoryImpl(private val entityManager: EntityManager) : CountryRepository {
    override fun existsByIso(isoCode: String): Boolean = entityManager
            .createNativeQuery("SELECT iso FROM country c WHERE iso = ?").setParameter(1, isoCode).resultList.size > 0

    override fun getLocalizedName(isoCode: String, language: String): EntityResponseDto? {
        val singleResult = entityManager.entityManagerFactory.createEntityManager().createNativeQuery("SELECT c.iso AS isoCode, cl.name AS localizedName FROM country_localization cl " +
                "INNER JOIN country c ON c.id = cl.country_id " +
                "INNER JOIN language l ON l.id = cl.language_id " +
                "WHERE c.iso = 'AU' AND l.language = 'en'", EntityResponseDto::class.java).singleResult
        return null
    }


}
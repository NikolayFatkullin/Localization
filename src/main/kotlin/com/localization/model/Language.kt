package com.localization.model

import javax.persistence.*

@Entity
@Table(name = "language")
class Language(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var language: String
) {
}
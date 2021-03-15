package com.localization.model

import javax.persistence.*

@Entity
@Table(name = "country_localization")
class CountryLocalization(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var name: String?,
    @field:OneToOne
    @field:JoinColumn(name = "language_id")
    var language: Language?,
    @field:OneToOne
    @field:JoinColumn(name = "country_id")
    var country: Country?
){
    constructor() : this(null, null, null, null)
}

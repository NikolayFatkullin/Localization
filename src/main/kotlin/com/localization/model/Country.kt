package com.localization.model

import javax.persistence.*

@Entity
@Table(name = "country")
class Country(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var name: String?,
    @field:OneToOne
    @field:JoinColumn(name = "language_id")
    var language: Language?,
    @field:OneToOne
    @field:JoinColumn(name = "iso_id")
    var isoCode: IsoCode?
){
    constructor() : this(null, null, null, null)
}

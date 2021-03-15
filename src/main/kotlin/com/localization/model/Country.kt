package com.localization.model

import javax.persistence.*

@Entity
@Table(name = "country")
class Country(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var iso: String?
) {
    constructor(): this(null, null)
}
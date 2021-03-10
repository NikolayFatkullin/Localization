package com.localization.model

import javax.persistence.*

@Entity
@Table(name = "country")
class Country(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var name: String?,
){
    constructor() : this(null, null)

    override fun toString(): String {
        return "Country(id=$id, name='$name')"
    }

}
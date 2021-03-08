package com.localization.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "localization")
class Localization () {
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var iso: String = ""
    @Column(columnDefinition = "hstore")
    var localized: String = ""

    override fun toString(): String {
        return "Localization(id=$id, iso='$iso', localized='$localized')"
    }
}
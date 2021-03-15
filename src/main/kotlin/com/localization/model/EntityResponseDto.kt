package com.localization.model

data class EntityResponseDto(val isoCode: String, val localizedName: String) {
    override fun toString(): String {
        return "EntityResponseDto(isoCode='$isoCode', localizedName='$localizedName')"
    }
}
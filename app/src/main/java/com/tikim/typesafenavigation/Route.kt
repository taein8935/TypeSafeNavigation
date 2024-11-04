package com.tikim.typesafenavigation

import kotlinx.serialization.Serializable


sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data class Detail(
        val id: String,
        val name: String,
        val description: String
    ) : Route
}
package com.users.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var id: String,
    val userName: String,
    val email: String,
    val password: String,
    var isLoggedIn: Boolean = false
)

val users = mutableListOf(
    User("2", "Itzik", "it@gmail.com", "Rz64", false)
)

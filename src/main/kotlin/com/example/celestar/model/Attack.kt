package com.example.celestar.model

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Attack(
    val attackName: String,
    val attackDamage: String,
    val attackToHit: String
)

package com.example.celestar.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("MonsterManual")
data class Creature(
    @Id
    val _id: String = UUID.randomUUID().toString(),
    val name: String,
    val ac: Int,
    val hp: Int,
    val attacks: List<Attack>?,
    val meta: Any?
)
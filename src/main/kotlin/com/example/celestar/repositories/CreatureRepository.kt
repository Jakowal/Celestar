package com.example.celestar.repositories

import com.example.celestar.model.Creature
import org.springframework.data.mongodb.repository.MongoRepository

interface CreatureRepository: MongoRepository<Creature, String> {
    fun findCreatureBy_id(_id: String): Creature?
    fun findAllByNameContaining(name: String): List<Creature>
    fun deleteAllByName(name: String): Int
    fun save(creature: Creature): Creature
}
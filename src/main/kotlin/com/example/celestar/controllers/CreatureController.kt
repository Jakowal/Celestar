package com.example.celestar.controllers

import com.example.celestar.model.Creature
import com.example.celestar.repositories.CreatureRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/creatures")
class CreatureController(
    private val creatureRepository: CreatureRepository
) {

    @GetMapping
    fun getAllCreatures(): ResponseEntity<List<Creature>> {
        val creatures = creatureRepository.findAll()
        return ResponseEntity.ok(creatures)
    }

    @GetMapping("/id={id}")
    fun getOneCreatureById(@PathVariable("id") id: String): ResponseEntity<Creature?> {
        val creature = creatureRepository.findCreatureBy_id(id)
        return ResponseEntity.ok(creature)
    }

    @GetMapping("/name={name}")
    fun getAllByName(@PathVariable("name") name: String): ResponseEntity<List<Creature>> {
        val creature = creatureRepository.findAllByName(name)
        return ResponseEntity.ok(creature)
    }
}
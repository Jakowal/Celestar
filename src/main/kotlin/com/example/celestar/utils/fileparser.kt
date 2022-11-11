package com.example.celestar.utils

import com.example.celestar.model.Attack
import com.example.celestar.model.Creature
import java.io.File
import java.util.*
import com.google.gson.Gson

/**
 * LEGACY
 * Used for generating a set of legacy monsters ready for JSON import from a .csv file
 */

/*fun main() {
    val inputFile = readFile()

    val creatureList = parseMonsters(inputFile)

    writeFile(creatureList.toString())
}*/

private fun readFile(filename: String = "src/main/kotlin/com/example/celestar/utils/MonsterManual.csv"): List<String> {
    val contents = mutableListOf<String>()
    File(filename).bufferedReader().forEachLine {
        contents.add(it)
    }
    return contents
}

private fun writeFile(data: String) {
    File("src/main/resources/generated/MonsterManual.json").writeText(data)
}

private fun parseMonsters(inputData: List<String>): List<String> {
    val parsedCreatures = mutableListOf<String>()
    inputData.forEach {
        val data = it.split(",")
        if (data.isNotEmpty()) {
            val attacks = parseAttacks(data.slice(3 until data.size).toMutableList())
            @Suppress("unused") val legacyTag = object { val legacy = true }
            val creature = Creature(UUID.randomUUID().toString(), data[0], data[1].toInt(), data[2].toInt(), attacks, legacyTag)

            parsedCreatures.add(Gson().toJson(creature))
        }
    }
    return parsedCreatures
}

private fun parseAttacks(attackData: MutableList<String>): List<Attack> {
    if (attackData.isEmpty()) return listOf()
    val parsedAttacks = mutableListOf<Attack>()
    while (attackData.isNotEmpty()) {
        parsedAttacks.add(Attack(attackData.removeAt(0),attackData.removeAt(0),attackData.removeAt(0)))
    }
    return parsedAttacks
}

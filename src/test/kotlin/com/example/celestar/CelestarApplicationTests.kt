package com.example.celestar

import com.example.celestar.model.Creature
import com.example.celestar.repositories.CreatureRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.UUID

@SpringBootTest
class CelestarApplicationTests {

	@Test
	fun contextLoads() {
	}
}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreatureControllerIntTest @Autowired constructor(
	private val creatureRepository: CreatureRepository,
	private val restTemplate: TestRestTemplate
) {
	private val defaultCreatureId = UUID.randomUUID().toString()
	private val defaultCreatureName = "Database Test Creature, will be deleted"

	@LocalServerPort
	protected var port: Int = 8080

	private fun getRootUrl(): String = "http://localhost:$port/creatures"

	private fun createTestCreature(creatureId: String = defaultCreatureId) = creatureRepository.save(Creature(creatureId, defaultCreatureName, 0, 0, null, null))
	@AfterEach
	fun cleanUp() {
		creatureRepository.deleteAllByName(defaultCreatureName)
	}

	@Test
	fun `should return all creatures`() {
		createTestCreature()

		val response = restTemplate.getForEntity(
			getRootUrl(),
			List::class.java
		)

		assertEquals(200, response.statusCode.value())
		assertNotNull(response.body)
	}

	@Test
	fun `should return all creatures by name`() {
		createTestCreature()
		createTestCreature(UUID.randomUUID().toString())

		val response = restTemplate.exchange(
			getRootUrl() + "/name=$defaultCreatureName",
			HttpMethod.GET,
			null,
			object : ParameterizedTypeReference<List<Creature?>?>() {}
		)

		assertEquals(200, response.statusCode.value())
		assertNotNull(response.body)
		assertEquals(2, response.body!!.size)
		val testCreature1: Creature = response.body!![0]!!
		val testCreature2: Creature = response.body!![1]!!
		assertEquals(defaultCreatureName, testCreature1.name)
		assertEquals(testCreature1.name, testCreature2.name)
	}

	@Test
	fun `should return single creature by id`() {
		createTestCreature()

		val response = restTemplate.getForEntity(
			getRootUrl() + "/id=$defaultCreatureId",
			Creature::class.java
		)

		assertEquals(200, response.statusCode.value())
		assertNotNull(response.body)
		assertEquals(defaultCreatureId, response.body!!._id)
	}
}
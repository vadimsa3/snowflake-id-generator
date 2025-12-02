package io.maksymdobrynin.snowflakegenerator

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class SnowflakeController(
	private val generator: Generator, private val podInfoReader: PodInfoReader
) {

	companion object {
		private val log: Logger = LoggerFactory.getLogger(SnowflakeController::class.java)
	}

	@GetMapping("/next-id")
	suspend fun generate(): Long {
		log.info(
			"Request on pod={}, podIp={}, node={}, nodeIp={}",
			podInfoReader.podName,
			podInfoReader.podIp,
			podInfoReader.nodeName,
			podInfoReader.nodeIp,
		)
		return generator.nextId()
	}

}

package io.maksymdobrynin.snowflakegenerator

import org.springframework.stereotype.Component

/**
 * Читает Kubernetes Downward API переменные окружения (POD_NAME, POD_IP, NODE_NAME, NODE_IP)
 */
@Component
class PodInfoReader {

	val podName: String = getEnvOrDefault("POD_NAME", "local-container")
	val podIp: String = getEnvOrDefault("POD_IP", "local-ip")
	val nodeName: String = getEnvOrDefault("NODE_NAME", "local-node")
	val nodeIp: String = getEnvOrDefault("NODE_IP", "local-ip")

	private fun getEnvOrDefault(env: String, default: String): String {
		val value = System.getenv(env)
		return if (!value.isNullOrEmpty()) value else default
	}
}

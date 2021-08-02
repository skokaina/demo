package com.example

import io.micronaut.runtime.Micronaut.*
import io.dekorate.kubernetes.annotation.KubernetesApplication
import io.dekorate.kubernetes.annotation.Label
import io.dekorate.kubernetes.annotation.Port
import io.dekorate.kubernetes.annotation.Probe
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*

@OpenAPIDefinition(
    info = Info(
            title = "demo",
            version = "0.0"
    )
)
object Api {
}
@KubernetesApplication(
    name = "demo",
    labels = [Label(key = "app", value = "demo")],
    ports = [Port(name = "http", containerPort = 8080)],
    livenessProbe = Probe(httpActionPath = "/health/liveness", initialDelaySeconds = 5, timeoutSeconds = 3, failureThreshold = 10),
    readinessProbe = Probe(httpActionPath = "/health/readiness", initialDelaySeconds = 5, timeoutSeconds = 3, failureThreshold = 10)
)
object Dekorate {
}
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.example")
		.start()
}


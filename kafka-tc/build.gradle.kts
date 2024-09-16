
val kafka_version: String by project
val junit_pioneer_version: String by project
val junit_version: String by project

plugins {
    kotlin("jvm") version "1.8.0"
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.16")

    testImplementation("org.junit-pioneer:junit-pioneer:$junit_pioneer_version")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit_version")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit_version")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junit_version")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit_version")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.mockito:mockito-core:5.13.0")

    testImplementation("org.testcontainers:testcontainers:1.20.1")
    testImplementation("org.testcontainers:kafka:1.20.1")
    testImplementation("org.apache.commons:commons-lang3:3.17.0")

    testImplementation("ch.qos.logback:logback-classic:1.5.7")
    testImplementation("ch.qos.logback:logback-core:1.5.7")

}


tasks.withType<Test> {
    useJUnitPlatform()
    systemProperties = System.getProperties().mapKeys { it.key.toString() }
}
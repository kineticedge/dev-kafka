plugins {
    java
}

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"("org.apache.kafka:kafka-clients:3.8.0")
    }

}
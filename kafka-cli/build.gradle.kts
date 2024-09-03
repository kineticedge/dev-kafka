plugins {
    kotlin("jvm") version "1.8.0" // Adjust the Kotlin version as needed
    application
    id("org.graalvm.buildtools.native") version "0.10.2" // Adjust the version as needed
}

application {
    mainClass.set("io.kineticedge.cli.KafkaList")
}

dependencies {
    implementation("org.slf4j:slf4j-nop:2.0.16")
}


graalvmNative {
    binaries {
        named("main") {
            buildArgs.add("--verbose")
            buildArgs.add("--no-fallback")
            buildArgs.add("--allow-incomplete-classpath")
            buildArgs.add("--initialize-at-run-time=org.apache.kafka.common.security.authenticator.SaslClientCallbackHandler")
            buildArgs.add("-H:AdditionalSecurityProviders=com.sun.security.sasl.Provider")

            val projectDir = project.projectDir.absolutePath.toString()
            val reflectConfigPath = "$projectDir/src/main/resources/META-INF/native-image/reflect-config.json"

            buildArgs.add("-H:ReflectionConfigurationFiles=$reflectConfigPath")
        }
    }
}


//nativeImage {
//    mainClass = 'com.example.YourMainClass'
//    imageName = 'your-application'
//    option '--initialize-at-run-time=org.apache.kafka.common.security.authenticator.SaslClientCallbackHandler'
//    option '-H:ConfigurationFileDirectories=src/main/resources/META-INF/native-image'
//}
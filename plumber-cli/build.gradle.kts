import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

plugins {
    java
}

jar.enabled = true
bootJar.enabled = true

dependencies {
    implementation(project(":plumber-core"))
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
}

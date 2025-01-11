plugins {
    java
    checkstyle
    id("com.diffplug.spotless") version "7.0.1"
}

spotless {
    java {
        leadingSpacesToTabs(4)
        removeUnusedImports()
        trimTrailingWhitespace()
        googleJavaFormat() // google's java style guide
    }
}

checkstyle {
    toolVersion = "10.21.1"
    configFile = file("${project.projectDir}/config/checkstyle/checkstyle.xml")
}

group = "me.dvcopae.tickets"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    runtimeOnly("org.postgresql:postgresql:42.7.4")

    testRuntimeOnly ("org.junit.platform:junit-platform-launcher")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()

    if (!project.hasProperty("skipDBTest") || project.findProperty("skipDBTest") != "true") {
        exclude("**/DBTest.*")
    }
}
plugins {
    `maven-publish`
    `java-library`
    id("java")
}

group = "org.densy.valea"
version = "1.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name.lowercase()
            version = project.version.toString()
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "densy"
            url = uri("https://repo.densy.org/snapshots")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }
}
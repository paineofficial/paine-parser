plugins {
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.spring") version "2.3.21"
    id("org.springframework.boot") version "4.1.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.paine.parser"
version = "0.0.1-SNAPSHOT"
description = "paine-parser"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

configurations.configureEach {
    exclude(
        group = "org.springframework.boot",
        module = "spring-boot-starter-logging"
    )
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc") {
        exclude(
            group = "org.springframework.boot",
            module = "spring-boot-starter-tomcat"
        )
    }

    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")

    implementation("com.microsoft.playwright:playwright:1.64.0")
    implementation("org.jsoup:jsoup:1.24.1")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("tools.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

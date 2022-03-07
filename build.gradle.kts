import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val springBootVersion = "2.6.4"
    val dependencyManagementVersion = "1.0.11.RELEASE"
    val kotlinVersion = "1.6.10"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version dependencyManagementVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")

    repositories {
        mavenCentral()
    }

    group = "com.ask"
    version = "0.0.1-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_11

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    allOpen {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.Embeddable")
        annotation("javax.persistence.MappedSuperclass")
    }
}

subprojects {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        implementation("com.querydsl:querydsl-jpa:5.0.0")
        kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
        kapt("jakarta.persistence:jakarta.persistence-api")
        kapt("jakarta.annotation:jakarta.annotation-api")

        kapt("org.springframework.boot:spring-boot-configuration-processor")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

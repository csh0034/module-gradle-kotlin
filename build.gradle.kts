import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
  kotlin("jvm")
  kotlin("kapt")
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  kotlin("plugin.spring")
  kotlin("plugin.jpa")
}

allprojects {
  repositories {
    mavenCentral()
  }
}

subprojects {
  apply {
    plugin("kotlin")
    plugin("kotlin-kapt")
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
    plugin("kotlin-spring")
    plugin("kotlin-jpa")
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

  allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
  }

  dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.querydsl:querydsl-jpa")
    kapt("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties["querydsl.version"]}:jpa")

    runtimeOnly("com.h2database:h2")

    kapt("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
  }

  springBoot {
    buildInfo {
      properties {
        System.getenv("BUILD_NUMBER")?.let {
          additional = mapOf("build_number" to it)
        }
      }
    }
  }

  tasks {
    bootJar {
      launchScript()
    }
    jar {
      enabled = false
    }
    test {
      useJUnitPlatform()
    }
    processResources {
      doFirst { loadSettingsProperties() }
      filesMatching("*.yml") {
        expand(project.properties)
      }
    }
  }
}

fun loadSettingsProperties() {
  // settingsPropertiesPath => ~/.gradle/gradle.properties
  val settingsProperty = project.properties["settingsPropertiesPath"] ?: "${project.rootDir}/settings/local.properties"

  println("[$settingsProperty] loading..")

  loadProperties(settingsProperty as String).forEach { entry ->
    val key = entry.key as String
    val value = entry.value as String

    println(" >> $key: [$value]")
    ext.set(key, value)
  }
}

tasks {
  jar {
    enabled = false
  }
  bootJar {
    enabled = false
  }
  test {
    dependsOn(":module-core:test")
  }
  build {
    dependsOn(":module-core:build")
  }
}

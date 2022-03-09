# Gradle Multi Module Kotlin

## Setting

### [Kotlin dsl user guide](https://docs.gradle.org/current/userguide/kotlin_dsl.html)

### plugins dsl 에서 version 변수 사용

- 관련 GitHub Issue
  - [Allow the plugin DSL to expand properties as part of the version](https://github.com/gradle/gradle/issues/1697)

### plugins, apply plugin

하단 두가지 플러그인은 동일함.

### [jvm and kotlin](https://kotlinlang.org/docs/gradle.html)

```kotlin
plugins {
    kotlin("jvm") version "1.6.10"
}

apply plugin: 'kotlin'
```

### [kapt and kotlin-apt](https://kotlinlang.org/docs/kapt.html)

```kotlin
plugins {
    kotlin("kapt") version "1.6.10"
}

apply plugin: 'kotlin-apt'
```

### [Packaging Executable and Plain Archives](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#packaging-executable.and-plain-archives)

- core 의 경우 bootJar 를 disable 시킨다.
- jar 생성일 경우 기본적으로 classifier 에 `plain` 값이 세팅되어있어 빈값으로 변경해야한다.

```kotlin
import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    archiveClassifier.set("")
}
```

### settings.gradle.kts 에서 gradle.properties 참조

- [Gradle, Settings](https://docs.gradle.org/current/dsl/org.gradle.api.initialization.Settings.html)
- [stack overflow, Get gradle property from settings](https://stackoverflow.com/questions/64644810/get-gradle-property-from-settings)

#### gradle.properties

```properties
kotlinVersion=1.6.10
springBootVersion=2.6.4
dependencyManagementVersion=1.0.11.RELEASE
```

#### settings.gradle.kts

```kotlin
val kotlinVersion: String by settings
val springBootVersion: String by settings
val dependencyManagementVersion: String by settings
```

## 참조
- [kotlin, gradle](https://kotlinlang.org/docs/gradle.html)
- [Gradle Kotlin DSL 정리, 우아한형제들 기술블로그](https://techblog.woowahan.com/2625/)

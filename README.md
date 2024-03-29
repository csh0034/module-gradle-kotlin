# Gradle Multi Module Kotlin

## [Gradle Build Lifecycle](https://docs.gradle.org/current/userguide/build_lifecycle.html)

### Build phases

- Initialization 
  - 단일/멀티 프로젝트 빌드 지원. 초기화 단계에서는 어느 프로젝트를 빌드하는지 결정하고 각각에 대해 Project 객체를 생성한다.
- Configuration
  - 빌드에 속하는 모든 프로젝트의 빌드 스크립트를 실행한다. 이를 통해 프로젝트 객체를 구성한다.
- Execution
  - 구성 단계에서 생성하고 설정된 테스크 중에 실행할 것을 결정한다.   
    이 때 gradle 명령행에 인자로 지정한 테스크 이름과 현재 디렉토리를 기반으로 테스크를 결정하여 선택된것들을 실행한다.

## Setting

### [Kotlin dsl user guide](https://docs.gradle.org/current/userguide/kotlin_dsl.html)

#### [Cross-configuring projects](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin_cross_project_configuration)

다른 프로젝트의 빌드 스크립트에서 프로젝트를 구성할 수 있는 메커니즘이다.   
일반적인 예는 루트 프로젝트 빌드 스크립트에서 하위 프로젝트를 구성하는 경우이다.

#### [Sharing Build Logic between Subprojects](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html)

하위 프로젝트 간에 빌드 논리를 공유하는 또 다른 권장되지 않는 방법은 `subprojects{}`, `allprojects{}` dsl 을 통한 구성이다.  
이는 빌드 구성을 하위 프로젝트에 주입하기 때문에 하위 프로젝트 빌드스크립트를 볼때 명확하지 않아 파악하기 어렵게 만든다.  

따라서 Gradle 에서 권장하는 방법은 플러그인 시스템을 사용하는것이다.  
[convention plugins 을 이용한 multi-module sample](https://docs.gradle.org/current/samples/sample_convention_plugins.html)

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

#### Kotlin DSL 을 활용한 Type Safe 한 구성 
- [Configuring Spring Boot using type-safe accessors](https://docs.gradle.org/current/userguide/migrating_from_groovy_to_kotlin_dsl.html#configuring-tasks)

```kotlin
tasks {
    bootJar {
        enabled = false
    }

    jar {
        archiveClassifier.set("")
    }
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

### 빌드환경별 프로퍼티 주입

#### build.gradle.kts

project property 에서 주입할 properties path 를 꺼내서 extra property 에 세팅

- extra property 에 주입할 경우 project.properties 에서 꺼낼 수 있음

```kotlin
project.properties["settingsPropertiesPath"]?.let {
    org.jetbrains.kotlin.konan.properties.loadProperties(it as String).forEach { entry ->
        val key = entry.key as String
        val value = entry.value as String
        ext.set(key, value)
    }
}

// yml 파일만 처리되도록 지정
processResources {
    filesMatching("*.yml") {
        expand(project.properties)
    }
}
```

#### gradle.properties 에 값 지정

~/.gradle/gradle.properties 추가

```properties
settingsPropertiesPath=/Users/../../local.properties
```

#### gradle build 할때 지정

```shell
$ clean build -PsettingsPropertiesPath=settings/dev.properties
$ clean build -PsettingsPropertiesPath=settings/st.properties
```

## 참조
- [kotlin, gradle](https://kotlinlang.org/docs/gradle.html)
- [Gradle Kotlin DSL 정리, 우아한형제들 기술블로그](https://techblog.woowahan.com/2625/)

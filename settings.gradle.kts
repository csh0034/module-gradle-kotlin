rootProject.name = "module-gradle-kotlin"

include("module-core")
include("module-api")

pluginManagement {
  val kotlinVersion: String by settings
  val springBootVersion: String by settings
  val dependencyManagementVersion: String by settings

  val pluginVersions = mapOf(
    "org.jetbrains.kotlin" to kotlinVersion,
    "org.jetbrains.kotlin.plugin" to kotlinVersion,
    "org.springframework" to springBootVersion,
    "io.spring" to dependencyManagementVersion
  )

  resolutionStrategy {
    eachPlugin {
      if (pluginVersions.containsKey(requested.id.namespace)) {
        useVersion(pluginVersions[requested.id.namespace])
      }
    }
  }
}

tasks {
  bootJar {
    enabled = false
  }
  jar {
    enabled = true
    archiveClassifier.set("")
  }
  test {
    useJUnitPlatform()
    exclude("**/*InitializeSchema.class")
    if (System.getProperty("db.init.skip") == null) {
      dependsOn(initializeSchema)
    }
  }
}

val initializeSchema by tasks.registering(Test::class) {
  useJUnitPlatform()
  include("**/InitializeSchema.class")
  doFirst { systemProperties("db.name" to "sample") }
}


dependencies {
    implementation(project(":module-core"))
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

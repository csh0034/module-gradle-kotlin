dependencies {
    implementation(project(":module-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

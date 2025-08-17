plugins {
    id("java")
}

group = "me.adamix.mercury.configuration.toml"
version = "0.0.1"

repositories {
}

dependencies {
    implementation(project(":api"))
    implementation("org.tomlj:tomlj:1.1.1")
}
plugins {
    id("java")
}

group = "me.adamix.mercury.configuration.modules.jda"
version = "0.1.0"

repositories {
}

dependencies {
    implementation(project(":api"))
    compileOnly("net.dv8tion:JDA:6.1.0")
}
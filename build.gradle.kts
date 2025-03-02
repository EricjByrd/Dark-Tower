plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.serialization)
    application
}

group = "io.github.ericjbyrd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.miglayout)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.flatlaf)
    implementation(libs.swingx)
    implementation(libs.serialization.json)
    implementation(libs.jide.common)
    implementation(libs.logback)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        languageVersion = libs.versions.java.map(JavaLanguageVersion::of)
        vendor = JvmVendorSpec.AZUL
    }
}

application {
    mainClass.set("io.github.ericjbyrd.partnershipproject.Main")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
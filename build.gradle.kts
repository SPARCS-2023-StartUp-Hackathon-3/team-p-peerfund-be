import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.12"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
    kotlin("kapt") version "1.6.10"
    id("com.google.cloud.tools.jib") version "3.1.2"
}

group = "kr.peerfund"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

sourceSets.main {
    withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
        kotlin.srcDir("$buildDir/generated/source/kapt/main")
    }
}

val swaggerVersion = "3.0.0"

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.querydsl:querydsl-jpa")
    kapt("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties["querydsl.version"]}:jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.hibernate.validator:hibernate-validator")
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    implementation("com.amazonaws:aws-java-sdk:1.12.394")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
    implementation("io.springfox:springfox-boot-starter:${swaggerVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("mysql:mysql-connector-java")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("io.mockk:mockk:1.13.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.4")
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
}

configurations {
    implementation.configure {
        exclude(module = "spring-boot-starter-tomcat")
        exclude("org.apache.tomcat")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jib {
    from {
        image = "adoptopenjdk/openjdk11:x86_64-ubuntu-jre-11.0.18_10"
    }
    to {
        image = "peerfund/backend"
        tags = mutableSetOf("latest")
    }
    container {
        jvmFlags = mutableListOf("-Xms2048m", "-Xmx2048m")
    }
}

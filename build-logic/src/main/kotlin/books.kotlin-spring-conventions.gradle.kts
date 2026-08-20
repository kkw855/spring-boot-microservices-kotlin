plugins {
  kotlin("jvm")
  kotlin("plugin.spring")
  kotlin("plugin.jpa")

  id("org.springframework.boot")
  id("io.spring.dependency-management")
}

group = "com.endsoullab"
version = "0.0.1-SNAPSHOT"

repositories {
  mavenCentral()
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
  }
}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll(
      "-Xjsr305=strict",
      "-Xannotation-default-target=param-property"
    )
  }
}

dependencyManagement {
  imports {
    mavenBom("org.testcontainers:testcontainers-bom:2.0.5")
  }
}

dependencies {
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")

  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  testImplementation("org.testcontainers:testcontainers-postgresql")
  testImplementation("org.testcontainers:testcontainers-junit-jupiter")
}

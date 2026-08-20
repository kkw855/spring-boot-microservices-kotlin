buildscript {
  dependencies {
    classpath("org.flywaydb:flyway-database-postgresql:13.3.0")
  }
}

plugins {
  id("books.kotlin-spring-conventions")
  id("org.flywaydb.flyway") version "13.3.0"
  id("org.jooq.jooq-codegen-gradle") version "3.19.15"
}

val dbUrl = System.getenv("JOOQ_DB_URL") ?: "jdbc:postgresql://localhost:5432/books_catalog_kotlin"
val dbUser = System.getenv("JOOQ_DB_USER") ?: "postgres"
val dbPassword = System.getenv("JOOQ_DB_PASSWORD") ?: "postgres"

flyway {
  url = dbUrl
  user = dbUser
  password = dbPassword
  locations = arrayOf("filesystem:src/main/resources/db/migration")
}

jooq {
  configuration {
    jdbc {
      driver = "org.postgresql.Driver"
      url = dbUrl
      user = dbUser
      password = dbPassword
    }
    generator {
      name = "org.jooq.codegen.KotlinGenerator"
      database {
        name = "org.jooq.meta.postgres.PostgresDatabase"
        inputSchema = "public"
        excludes = "flyway_schema_history"
      }
      target {
        packageName = "com.endsoullab.bookstore.catalog.jooq"
        directory = "build/generated-sources/jooq"
      }
    }
  }
}

kotlin {
  sourceSets {
    main {
      kotlin.srcDir("build/generated-sources/jooq")
    }
  }
}

tasks.named("jooqCodegen") {
  dependsOn("flywayMigrate")
}

dependencies {
  // jOOQ 3.19.15 codegen이 최신 pg jdbc(42.7.5+)의 getImportedKeys 메타데이터와 호환 문제가 있어 버전 고정
  jooqCodegen("org.postgresql:postgresql:42.7.4")

  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-actuator")

  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-jooq")

  implementation("org.jetbrains.kotlin:kotlin-reflect")
  runtimeOnly("org.postgresql:postgresql")

  implementation("org.springframework.boot:spring-boot-starter-flyway")
  implementation("org.flywaydb:flyway-database-postgresql")
}

plugins {
	id("org.springframework.boot") version "2.7.11"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	id("org.jetbrains.kotlin.jvm") version "1.6.21"
	id("org.jetbrains.kotlin.plugin.spring") version "1.6.21"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.6.21"
//	id "org.jetbrains.kotlin.plugin.lombok" version "1.8.21"
//	id "io.freefair.lombok" version "5.3.0"
}

group = "com.tempo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	implementation("org.postgresql:postgresql:42.6.0")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springdoc:springdoc-openapi-ui:1.7.0")

	implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
	implementation("ch.qos.logback:logback-classic:1.2.6")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.0")
	implementation("com.h2database:h2:2.1.214")

	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-aop")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("org.mockito:mockito-core:2.28.2")
	testImplementation("io.mockk:mockk:1.10.4")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("com.ninja-squad:springmockk:3.0.1")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")

}

//
//tasks.withType(KotlinCompile) {
//	kotlinOptions {
//		freeCompilerArgs = ["-Xjsr305=strict"]
//		jvmTarget = "11"
//	}
//}
//
//tasks.named("test") {
//	useJUnitPlatform()
//}

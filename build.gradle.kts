plugins {
    kotlin("multiplatform") version "1.9.20"
}

group = "org.llesha"
version = "1.0"

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(false)
                }
            }
            webpackTask {
                output.libraryTarget = "commonjs2"
//                outputDirectory = file("../site/js/interpreter")
            }
//            distribution {
//                directory = file("../site")
//            }
        }
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
    }
}
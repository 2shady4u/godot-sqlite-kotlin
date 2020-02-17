val platform: String by project
val android_arch: String by project

buildscript {
    repositories {
        mavenLocal()
        maven("https://dl.bintray.com/utopia-rise/kotlin-godot")
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
        classpath("org.godotengine.kotlin:godot-gradle-plugin:1.0.1")
    }
}

plugins {
    id("org.jetbrains.kotlin.multiplatform") version ("1.3.61")
}

apply(plugin = "godot-gradle-plugin")

repositories {
    mavenLocal()
    maven("https://dl.bintray.com/utopia-rise/kotlin-godot")
    jcenter()
}

kotlin {
    sourceSets {
        sourceSets.create("windowsMain")
        configure(listOf(
                sourceSets["windowsMain"]
        )) {
            this.kotlin.srcDir("src/main/kotlin")
        }


        configure<org.godotengine.kotlin.gradleplugin.ConfigureGodotConvention> {
            this.configureGodot(listOf(
                    sourceSets["windowsMain"]
            )) {
                sourceSet {
                    kotlin.srcDirs("src/main/kotlin")
                }

                libraryPath("addons/godot-kotlin-sqlite/bin/SQLiteWrapper.gdnlib")
                generateGDNS("${project.rootDir.absolutePath}/../demo")

                configs(
                        "src/main/kotlin/classes.json"
                )
            }
        }
    }

    val targets = if (project.hasProperty("platform")) {
        when (platform) {
            "windows" -> listOf(targetFromPreset(presets["godotMingwX64"], "windows"))
            "linux" -> listOf(targetFromPreset(presets["godotLinuxX64"], "linux"))
            "macos" -> listOf(targetFromPreset(presets["godotMacosX64"], "macos"))
            "android" -> if (project.hasProperty("android_arch")) {
                when (android_arch) {
                    "X64" -> listOf(targetFromPreset(presets["godotAndroidNativeX64"], "androidX64"))
                    "arm64" -> listOf(targetFromPreset(presets["godotAndroidNativeArm64"], "androidArm64"))
                    else -> listOf(targetFromPreset(presets["godotAndroidNativeArm64"], "androidArm64"))
                }
            } else listOf(targetFromPreset(presets["godotAndroidNativeArm64"], "androidArm64"))
            else -> listOf(targetFromPreset(presets["godotMacosX64"], "macos"))
        }
    } else {
        listOf(
                targetFromPreset(presets["godotMingwX64"], "windows")
        )
    }

    targets.forEach {
        it.compilations.getByName("main") {
            if (this is org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeCompilation) {
                println("Configuring target ${this.target.name}")
                cinterops {
                    create("SQLite") {
                        defFile("src/main/c_interop/sqlite.def")
                        includeDirs("src/main/c_interop")
                    }
                }
                this.target.binaries {
                    sharedLib(listOf(org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG))
                }
                this.target.compilations.all {
                    dependencies {
                        implementation("org.godotengine.kotlin:godot-library:1.0.0")
                        implementation("org.godotengine.kotlin:annotations:0.0.1-SNAPSHOT")
                    }
                }
            } else {
                System.err.println("Not a native target! TargetName: ${target.name}")
            }
        }
    }
}
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()

        // Para usar tu repositorio local
        maven {
            url = uri("file:///D:/MTRI/LocalRepository")
        }

        // Para usar JitPack
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "MTRILogicLibraryProject"

include(":app")
include(":modelable")
include(":inflatable")
include(":fragmentable")
include(":recyclable")

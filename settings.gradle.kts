dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        libs {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

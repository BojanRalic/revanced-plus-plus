group = "app.revanced"

patches {
    about {
        name = "Revanced++ Patches"
        description = "Patches for Revanced++"
        source = "https://github.com/CodeVibing/revanced-plus-plus"
        author = "CodeVibing"
        contact = ""
        website = "https://github.com/CodeVibing/revanced-plus-plus"
        license = "GNU General Public License v3.0"
    }
}

dependencies {
    // Required due to smali, or build fails. Can be removed once smali is bumped.
    implementation(libs.guava)

    implementation(libs.apksig)

    // Android API stubs defined here.
    compileOnly(project(":patches:stub"))
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xexplicit-backing-fields",
            "-Xcontext-parameters"
        )
    }
}

publishing {
    repositories {
        maven {
            name = "githubPackages"
            url = uri("https://maven.pkg.github.com/revanced/revanced-patches")
            credentials(PasswordCredentials::class)
        }
    }
}

apply(from = "strings-processing.gradle.kts")

plugins {
    id("com.android.application")
}

android {
    namespace = "com.mtrilogic.mtrilogicproject"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // ViewBinding
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":fragmentable"))
    implementation(project(":inflatable"))
    implementation(project(":modelable"))
    implementation(project(":recyclable"))

    // Usando repositorio local:
    /*implementation("com.mtrilogic:modelable:0.0.0")
    implementation("com.mtrilogic:fragmentable:0.0.0")
    implementation("com.mtrilogic:inflatable:0.0.0")
    implementation("com.mtrilogic:recyclable:0.0.0")*/

    // Alternativamente, usando JitPack (comentado si usas local)
    // implementation("com.github.TU_USUARIO:MTRILogicLibraryProject:0.0.1")

    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Xlint:deprecation")
}

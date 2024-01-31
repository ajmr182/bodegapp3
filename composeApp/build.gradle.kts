import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.icerockResources)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        getByName("androidMain").dependsOn(commonMain.get())
        getByName("desktopMain").dependsOn(commonMain.get())

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            api(libs.icerock.resources.library)
            api(libs.icerock.resources)
            implementation(libs.koinCore)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.transitions)
            implementation(libs.kotlinx.serialization.json)
            implementation(project.dependencies.platform("io.github.jan-tennert.supabase:bom:1.3.2"))
            implementation("io.github.jan-tennert.supabase:postgrest-kt")
            implementation("io.github.jan-tennert.supabase:realtime-kt")
            implementation("io.github.jan-tennert.supabase:gotrue-kt")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "com.ajmr182.bodegaap3"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

   /* sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")*/
   /* sourceSets["main"].resources.srcDirs("src/commonMain/resources")*/

    defaultConfig {
        applicationId = "com.ajmr182.bodegaap3"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.ajmr182.bodegaap3"
            packageVersion = "1.0.0"
        }
    }
}

multiplatformResources{
    multiplatformResourcesPackage = "com.ajmr182.bodegaap3"
    multiplatformResourcesClassName = "SharedRes"
   /* this.resourcesPackage = "com.ajmr182.bodegaap3"*/
}
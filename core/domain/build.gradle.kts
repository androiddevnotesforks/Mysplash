plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    val sdkVersion: Int by rootProject.extra
    val minSdkVersion: Int by rootProject.extra
    val jvmTargetVersion: String by rootProject.extra
    val sourceCompatibilityVersion:JavaVersion by rootProject.extra
    val targetCompatibilityVersion:JavaVersion by  rootProject.extra

    namespace = "com.timurkhabibulin.domain"
    compileSdk = sdkVersion

    defaultConfig {
        minSdk = minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = sourceCompatibilityVersion
        targetCompatibility = targetCompatibilityVersion
    }
    kotlinOptions {
        jvmTarget = jvmTargetVersion
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")

    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    implementation ("androidx.paging:paging-compose:3.2.1")
/*    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")*/
}

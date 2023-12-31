import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.subject.remote"
    compileSdk = 33

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":data"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    api(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)

    implementation(libs.retrofit)
    api(libs.retrofit.converter)

    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)
}
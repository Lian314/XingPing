plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.constellationapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.constellationapp"
        minSdk = 26 // 升级最低 API 级别到 26
        targetSdk = 33 // 保持 targetSdkVersion 为 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        buildConfig = true // 明确启用 buildConfig
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // 更新到最新的 Compose 版本
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.1") // 更新导航依赖
    implementation("androidx.navigation:navigation-compose:2.7.1") // 更新导航依赖
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // 添加 OkHttp 和 Jsoup 依赖
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("org.jsoup:jsoup:1.15.4")

    // Jetpack Compose 依赖
    implementation("androidx.compose.ui:ui:1.5.3") // 更新 Compose 版本
    implementation("androidx.compose.material:material:1.5.3") // 更新 Compose 版本
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3") // 更新 Compose 版本
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3") // 更新 Compose 版本
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")

    // Jetpack Compose 图标库
    implementation("androidx.compose.material:material-icons-core:1.5.3") // 更新 Compose 版本
    implementation("androidx.compose.material:material-icons-extended:1.5.3") // 更新 Compose 版本

    // Gson 库
    implementation("com.google.code.gson:gson:2.8.9")

    // ExoPlayer 依赖
    implementation("com.google.android.exoplayer:exoplayer:2.19.1") // 替换 XX 为最新版本号
}




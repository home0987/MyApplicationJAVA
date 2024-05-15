plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.myapplication_java"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication_java"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // BuildConfig 클래스 추가
        buildConfigField ("String", "API_URL", "\"http://192.168.137.86:5009\"")
        buildConfigField ("int", "PARKING_SPACE_STATUS_UPDATE_INTERVAL_MILLIS", "5000")

        // 초기 주차 공간 상태 배열은 별도로 설정하는 것이 좋습니다.
        // (예: string.xml 파일 사용)
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

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation ("com.localebro:okhttpprofiler:1.0.8")
    implementation ("com.squareup.retrofit2:retrofit:2.10.0")
    implementation ("com.google.code.gson:gson:2.8.8")
    implementation ("com.squareup.retrofit2:converter-gson:2.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.android.volley:volley:1.2.1")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2 (okhttp-5.0.0-alpha.2.jar)")
    implementation ("com.squareup.retrofit2:retrofit:2.10.0 (retrofit-2.10.0.jar)")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
}

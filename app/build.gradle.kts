plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace 'com.eutalix.safbridge'
    compileSdk 34

    defaultConfig {
        applicationId "com.eutalix.safbridge"
        // Min SDK 23 (Android 6.0) to support older devices often used with ZArchiver
        minSdk 23
        targetSdk 34
        versionCode 1
        versionName "1.0"
        
        vectorDrawables {
            useSupportLibrary true
        }
    }

    buildTypes {
        release {
            minifyEnabled true
            shrinkResources true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    
    buildFeatures {
        compose true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion '1.5.1'
    }
    
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    // Vital dependencies for the plugin logic
    implementation(libs.androidx.documentfile)
    implementation(libs.androidx.material_icons_extended)
    
    debugImplementation(libs.androidx.ui.tooling)
}
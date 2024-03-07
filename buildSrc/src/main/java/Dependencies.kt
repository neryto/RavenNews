import Dependencies.ANDROID_ROOM_COMPILER
import Dependencies.ANDROID_ROOM_KTX
import Dependencies.ANDROID_ROOM_RUNTIME
import Dependencies.activityKtx
import Dependencies.appCompat
import Dependencies.coroutines
import Dependencies.espressoCore
import Dependencies.extJunit
import Dependencies.flexBox
import Dependencies.fragmentKtx
import Dependencies.glide
import Dependencies.gson
import Dependencies.hilt
import Dependencies.hiltCompiler
import Dependencies.junit
import Dependencies.kotlinCore
import Dependencies.lifeCycle
import Dependencies.logginInterceptor
import Dependencies.material
import Dependencies.navigationFragment
import Dependencies.navigationUI
import Dependencies.okHttp
import Dependencies.retrofit
import Dependencies.retrofitConverter
import ext.androidTestImplementation
import ext.implementation
import ext.kapt
import ext.testImplementation
import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    /** General **/

    const val kotlinCore = "androidx.core:core-ktx:${Versions.kotlinCore}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val lifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val flexBox = "com.google.android.flexbox:flexbox:${Versions.flexBox}"
    const val ANDROID_ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.room}"
    const val ANDROID_ROOM_KTX = "androidx.room:room-ktx:${Versions.room}"
    const val ANDROID_ROOM_COMPILER = "androidx.room:room-compiler:${Versions.room}"



    /** Testing **/

    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.ext.test.ext:junit:${Versions.extJunit}"
    const val espressoCore = "androidx.ext.test.espresso:espresso-core:${Versions.espresso}"

    /** Network **/

    val gson by lazy { "com.google.code.gson:gson:${Versions.gsonVersion}" }
    val okHttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}" }
    val retrofitConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}" }
    val logginInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}" }

    /** DI **/

    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }

    /** Navigation **/

    val navigationUI by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}" }
    val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}" }
}

/** Dependencies **/

fun DependencyHandler.general() {
    implementation(material)
    implementation(appCompat)
    implementation(lifeCycle)
    implementation(coroutines)
    implementation(kotlinCore)
    implementation(activityKtx)
    implementation(fragmentKtx)
    implementation(glide)
    implementation(flexBox)
    implementation(ANDROID_ROOM_RUNTIME)
    implementation(ANDROID_ROOM_KTX)
    kapt(ANDROID_ROOM_COMPILER)
}

fun DependencyHandler.testing() {
    testImplementation(junit)
    androidTestImplementation(extJunit)
    androidTestImplementation(espressoCore)
}

fun DependencyHandler.network() {
    implementation(gson)
    implementation(okHttp)
    implementation(retrofit)
    implementation(logginInterceptor)
    implementation(retrofitConverter)
}

fun DependencyHandler.di() {
    kapt(hiltCompiler)
    implementation(hilt)
}

fun DependencyHandler.navigation() {
    implementation(navigationUI)
    implementation(navigationFragment)
}

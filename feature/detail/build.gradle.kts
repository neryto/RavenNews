import ext.implementation

plugins {
    id(ModulePlugin.MODULE_NAME)
}

android {
    namespace = "com.raven.detail"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    di()
    general()
    implementation(project(":core"))
    implementation(project(":database"))

}
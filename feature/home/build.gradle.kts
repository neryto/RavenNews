import ext.implementation

plugins {
    id(ModulePlugin.MODULE_NAME)
}

android {
    namespace = "com.raven.home"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    di()
    general()
    testing()
    network()

    implementation(project(":core"))

}

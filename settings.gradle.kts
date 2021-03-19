rootProject.name = "KotlinMVVM"
include(":app")
include(":model")
include(":bean")
includeBuild("plugin")

val useLocal: String by settings
if (useLocal.toBoolean()) {
    include(":common")
    project(":common").projectDir = File("../Github/common")
}
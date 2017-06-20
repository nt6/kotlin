
apply {
    plugin("kotlin")
}

//val shadowContentsCfg = configurations.create("shadowContents")

//val projectsToShadow = listOf(
//        //android-extensions-jps
//           ":build-common",
//           ":compiler:cli-common",
//           ":compiler:compiler-runner",
//           ":compiler:daemon-client",
//           ":compiler:daemon-common",
//           ":core",
//           ":idea:idea-jps-common",
//           ":compiler:preloader",
//           ":compiler:util",
//           ":core:util.runtime")

dependencies {
    testRuntime(ideaSdkCoreDeps("*.jar"))
    testRuntime(ideaSdkDeps("*.jar"))
    testRuntime(ideaSdkDeps("*.jar", subdir = "jps/test"))
    testRuntime(ideaSdkDeps("*.jar", subdir = "jps"))
    compile(project(":build-common"))
    compile(project(":core"))
    compile(project(":compiler:compiler-runner"))
    compile(project(":compiler:daemon-common"))
    compile(project(":compiler:daemon-client"))
    compile(project(":compiler:frontend.java"))
    compile(project(":compiler:preloader"))
    compile(project(":idea:idea-jps-common"))
    compile(ideaSdkDeps("jps-builders", "jps-builders-6", subdir = "jps"))
    buildVersion()
//    shadowContentsCfg(files("$rootDir/dependencies/native-platform-uberjar.jar"))
//    projectsToShadow.forEach {
//        shadowContentsCfg(projectDepIntransitive(it))
//    }
    testCompile(project(":compiler:tests-common"))
    testCompile(project(":compiler:incremental-compilation-impl"))
//    testCompileOnly(ideaSdkDeps("idea"))
    testCompileOnly(ideaSdkDeps("jps-build-test", subdir = "jps/test"))
    testCompile(commonDep("junit:junit"))
    testCompile(project(":build-common", configuration = "tests-jar"))
    testRuntime(project(":compiler"))
    testRuntime(project(":compiler:util"))
//    testRuntime(project(":prepare:compiler", configuration = "default"))
}

configureKotlinProjectSourcesDefault()
configureKotlinProjectResourcesDefault()
configureKotlinProjectResources("resources", sourcesBaseDir = rootDir)
//configureKotlinProjectNoTests()
configureKotlinProjectTests("test", sourcesBaseDir = File(projectDir, "jps-tests"))
configureKotlinProjectTestResources("testData")

fixKotlinTaskDependencies()

tasks.withType<Test> {
    jvmArgs("-ea", "-XX:+HeapDumpOnOutOfMemoryError", "-Xmx1250m", "-XX:+UseCodeCacheFlushing", "-XX:ReservedCodeCacheSize=128m", "-Djna.nosys=true")
    workingDir = rootDir
    systemProperty("idea.is.unit.test", "true")
    forkEvery = 100
    ignoreFailures = true
}

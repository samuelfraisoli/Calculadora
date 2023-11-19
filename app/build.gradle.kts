//archivo de configuración build.gradle.kts para una aplicación Android escrita en Kotlin.
// - Este archivo se utiliza para definir la configuración del proyecto Android, incluyendo las dependencias, propiedades de compilación y otros ajustes necesarios para compilar y construir la aplicación. En este caso, es un archivo de construcción de Gradle para un proyecto Android basado en Kotlin.


//plugins
// - Define los plugins que se usan en el proyecto
// - com.android.application -> Plugin que establece que es una aplicacion Android
// - org.jetbrains.kotlin.android -> Plugin es específico para proyectos que utilizan Kotlin en Android
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

//android
// - Sección de configuración de las propiedades de android en el proyecto
// - namespace -> ?
// - compileSDK ->
android {
    namespace = "com.example.calculadora"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.calculadora"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    //ViewBinding
    //  - Está configurando el "View Binding" en tu proyecto. El "View Binding" es una característica de Android que te facilita el acceso a las vistas (elementos de la interfaz de usuario) en un diseño XML desde el código de Kotlin o Java.
    // -Se introdujo para mejorar la forma en que los desarrolladores acceden y manipulan las vistas en sus aplicaciones Android, reemplazando en gran medida el uso de findViewById y reduciendo la posibilidad de errores de tiempo de ejecución debido a referencias nulas.

    //Las clases que genera ViewBinding:
    // - Estas clases se generan cuando se habilita View Binding en un proyecto Android. Cada archivo de diseño XML en el proyecto tiene su propia clase de enlace correspondiente. Por ejemplo, si tienes un archivo de diseño llamado activity_main.xml, View Binding generará una clase llamada ActivityMainBinding. (en el Main, lo que hacemos es guardar una clase de estas en una variable llamada "binding")

    //Habilitar viewBinding estableciendo enable en true permite que Android Studio genere automáticamente las clases de View Binding para tus diseños XML, lo que facilita el acceso a las vistas en tu código Kotlin de manera más segura y eficiente que usar findViewById manualmente.
    viewBinding {
        enable = true;
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
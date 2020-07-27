@file:Suppress("SpellCheckingInspection")

object Versions {
    object Kotlin {
        const val plugin = "1.4-M3"
        const val stdlib = "1.3.72"
//        const val coroutines = "1.3.8"
    }

    const val kotlinPoet = "1.6.0"

    const val kaseChange = "1.3.0"


    const val jUnit = "5.6.2"
    const val kotest = "4.1.2"
}

object Deps {
//    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"

    const val kotlinPoet = "com.squareup:kotlinpoet:${Versions.kotlinPoet}"

    const val kaseChange = "net.pearx.kasechange:kasechange-jvm:${Versions.kaseChange}"

    object Test {
        const val jUnit = "org.junit.jupiter:junit-jupiter:${Versions.jUnit}"

        object Kotest {
            const val runner = "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}"
            const val assertions = "io.kotest:kotest-assertions-core-jvm:${Versions.kotest}"
            const val properties = "io.kotest:kotest-property-jvm:${Versions.kotest}"

            const val console = "io.kotest:kotest-runner-console-jvm:${Versions.kotest}"
        }

    }
}

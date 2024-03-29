package com.users

import com.users.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}


fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureSecurity()
    configureRouting()
}

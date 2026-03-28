package com.example

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import kotlinx.serialization.Serializable

@Serializable
data class Stock(val symbol: String, val price: Double)

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    install(ContentNegotiation) {
        json()
    }

    routing {

        // Exercise 1
        get("/") {
            call.respondText("Server is online at Lehman College.")
        }

        // Exercise 2
        get("/greet/{name}") {
            val name = call.parameters["name"]
            call.respondText("Hello, $name! Welcome to CMP 269.")
        }

        // Exercise 3
        get("/grade/{studentId}") {
            val grades = mapOf("123" to 95, "456" to 82)
            val studentId = call.parameters["studentId"]
            val grade = grades[studentId]
            if (grade != null) {
                call.respondText("Grade for student $studentId: $grade")
            } else {
                call.respond(HttpStatusCode.NotFound, "Student not found")
            }
        }

        // Exercise 4
        staticResources("/static", "static")

        // Exercise 5
        get("/api/stock/{symbol}") {
            val symbol = call.parameters["symbol"] ?: "UNKNOWN"
            val stock = Stock(symbol, 150.25)
            call.respond(stock)
        }
    }
}
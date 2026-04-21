package com.example

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import kotlinx.serialization.Serializable
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream
import java.util.concurrent.ConcurrentHashMap

import java.awt.Color
import java.awt.image.BufferedImage
import javax.imageio.ImageIO


// 1. DATA MODELING (Kotlin Fundamentals)
@Serializable
data class Student(
    val id: String,
    val name: String,
    val major: String?, // Nullable as per requirements
    val accessLevel: Int
)

fun main() {
    // 2. THREAD-SAFE DATABASE
    val studentDb = ConcurrentHashMap<String, Student>().apply {
        put("12345", Student("12345", "Alice Smith", "Computer Science", 5))
        put("67890", Student("67890", "Bob Jones", null, 3)) // Will test Elvis operator
    }

    embeddedServer(Netty, port = 8080) {
        module(studentDb)
    }.start(wait = true)
}

fun Application.module(studentDb: ConcurrentHashMap<String, Student>) {
    // 3. CONTENT NEGOTIATION (JSON API)
    install(ContentNegotiation) {
        json()
    }

    routing {
        // A. STATIC PORTAL
        staticResources("/", "static")

        // B. STUDENT API (Path Parameters & Null Safety)
        get("/api/student/{id}") {
            val id = call.parameters["id"]

            // If no ID was passed, return 400
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing student ID")
                return@get
            }


            val student = studentDb[id]

            // If student not found, return 404
            if (student == null) {
                call.respond(HttpStatusCode.NotFound, "Student with ID $id not found")
                return@get
            }


            val response = student.copy(major = student.major ?: "Undecided")

            call.respond(response)
        }

        // C. QR GENERATOR (Query Parameters & Image Response)
        get("/generate-id") {
            val sid = call.request.queryParameters["sid"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val student = studentDb[sid]
            val qrText = if (student != null) {
                "ID: ${student.id}\nName: ${student.name}\nMajor: ${student.major ?: "Undecided"}"
            } else {
                "Student Not Found"
            }

            // 1. Generate the standard QR BitMatrix
            val qrWriter = QRCodeWriter()
            val bitMatrix = qrWriter.encode(qrText, BarcodeFormat.QR_CODE, 400, 400)

            // 2. Convert BitMatrix to a BufferedImage
            val qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix)
            val g = qrImage.createGraphics()

            try {
                // 3. Load the Lehman logo
                val logoStream = object {}.javaClass.getResourceAsStream("/static/lehman_logo.png")
                if (logoStream != null) {
                    val logo = ImageIO.read(logoStream)


                    val logoSize = 80
                    val x = (qrImage.width - logoSize) / 2
                    val y = (qrImage.height - logoSize) / 2

                    // 5. Draw a white square
                    g.color = Color.WHITE
                    g.fillRect(x, y, logoSize, logoSize)

                    // 6. Paint the logo in the center
                    g.drawImage(logo, x, y, logoSize, logoSize, null)
                }
            } finally {
                g.dispose()
            }

            // 7. Display the lehman bolt
            val os = ByteArrayOutputStream()
            ImageIO.write(qrImage, "png", os)
            call.respondBytes(os.toByteArray(), ContentType.Image.PNG)
        }
    }
}
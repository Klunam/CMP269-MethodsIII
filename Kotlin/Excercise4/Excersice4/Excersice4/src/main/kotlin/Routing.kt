package com.example

import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.ByteArrayOutputStream
import java.nio.file.FileSystems

fun Application.configureRouting() {
    routing {

        // Exercise 1: Save QR code to a file
        get("/save-qr") {
            val content = "yourname@lehman.cuny.edu"
            val fileName = "my_email.png"

            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 300, 300)
            val path = FileSystems.getDefault().getPath(fileName)
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path)

            call.respondText("QR code saved as $fileName")
        }

        // Exercise 2: QR code in memory as ByteArrayOutputStream
        get("/memory-qr") {
            val content = "yourname@lehman.cuny.edu"

            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 300, 300)
            val outputStream = ByteArrayOutputStream()
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)

            call.respondText("QR code generated in memory! Size: ${outputStream.size()} bytes")
        }

        // Exercise 3: QR as a Service
        get("/qr") {
            val text = call.request.queryParameters["text"]

            if (text != null) {
                val writer = QRCodeWriter()
                val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 300, 300)
                val outputStream = ByteArrayOutputStream()
                MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)

                call.response.header(HttpHeaders.ContentType, "image/png")
                call.respondBytes(outputStream.toByteArray())
            } else {
                call.respondText("Please provide a text parameter. Example: /qr?text=Hello")
            }
        }
    }
}
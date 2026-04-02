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

fun saveQRCode(content: String, fileName: String) {
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 300, 300)
    val path = FileSystems.getDefault().getPath(fileName)
    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path)
}

fun generateQRStream(content: String): ByteArrayOutputStream {
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 300, 300)
    val outputStream = ByteArrayOutputStream()
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)
    return outputStream
}

fun Application.configureRouting() {
    
    // Saves the file when the server starts up
    saveQRCode("kevin.mitrecorona@lc.cuny.edu", "my_email.png")

    routing {

    
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
                call.respondText("Please provide a text parameter")
            }
        }
    }
}

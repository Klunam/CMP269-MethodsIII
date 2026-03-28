// Exercise 1, data class WebResponse.

data class WebResponse(
    val statusCode: Int,
    val statusMessage: String,
    val body: String?
)

// Exercise 2, Write a function describeStatus(code: Int): String.

fun describeStatus(code: Int): String {
    return when (code) {
        in 200..299 -> "Success: The request was fulfilled."
        in 400..499 -> "Client Error: Check your URL or parameters."
        in 500..599 -> "Server Error: The Lehman Server is having trouble."
        else -> "Unknown status code."
    }
}

// Exercise 3, Create a function routeRequest(path: String, user: String?).

fun routeRequest(path: String, user: String?) {
    
    val result = when (path) {
        "/home" -> "Welcome to the Lehman Homepage, ${user ?: "Guest"}!"
        "/grades" -> {
            if (user == null) "Error: Unauthorized access to grades."
            else "Loading grades for $user..."
        }
        else -> "404: Path $path not found."
    }
    println(result)
}

fun main() {
    // Exercise 1
    val successResponse = WebResponse(200, "OK", "{\"message\": \"Here is your data\"}")
    val notFoundResponse = WebResponse(404, "Not Found", null)

    println(successResponse)
    println(notFoundResponse)

    println()

    // Exercise 2
    println(describeStatus(201))
    println(describeStatus(404))
    println(describeStatus(503))

    println()

    // Exercise 3 
    routeRequest("/home", "Kevin")
    routeRequest("/home", null)
    routeRequest("/grades", null)
    routeRequest("/grades", "Kevin")
    routeRequest("/about", "Kevin")
}
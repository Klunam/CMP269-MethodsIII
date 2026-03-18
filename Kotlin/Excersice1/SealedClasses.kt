sealed class EnrollmentStatus {
    data class Success(val courseCode: String) : EnrollmentStatus()
    data class Error(val message: String) : EnrollmentStatus()
    object Loading : EnrollmentStatus()
}

fun printStatus(status: EnrollmentStatus) {
    when (status) {
        is EnrollmentStatus.Success -> println("Enrolled in: ${status.courseCode}")
        is EnrollmentStatus.Error   -> println("Error: ${status.message}")
        is EnrollmentStatus.Loading -> println("Loading...")
       
    }
}

fun main() {
    printStatus(EnrollmentStatus.Success("CMP269"))
    printStatus(EnrollmentStatus.Error("Course is full"))
}
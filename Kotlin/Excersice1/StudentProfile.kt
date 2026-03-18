// Excersice 1
fun main(){


var studentName: String = "Kevin"
val middleName: String? = null

val displayMiddle = middleName ?: "No Middle Name"

// printing a greeting

println("Welcome, $studentName ($displayMiddle) !")


}
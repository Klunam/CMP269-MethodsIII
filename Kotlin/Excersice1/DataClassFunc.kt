data class Laptop(val brand: String, val ramGB: Int)

fun Int.toLehmanGigabytes(): String {
    return "$this GB (Lehman Standard)"
}

fun main(){
    val laptop1 = Laptop("Mackbook Pro", 16)
    val laptop2 = Laptop("ASUS", 8)

    println(laptop1)
    println(laptop2)

  println(laptop1.ramGB.toLehmanGigabytes()) 
  println(laptop2.ramGB.toLehmanGigabytes())
}
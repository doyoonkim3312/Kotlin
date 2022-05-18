package Functional_Kotlin_Practice

fun main(args: Array<String>) {
    dataClassCanonicalMethodExample()
}

// Data class
// Data class is mainly for storing data, just like a JSON or Protobuff.
// To declare data class, there are three core requirements:
// 1. There must be at least one parameter in default constructor.
// 2. A parameter in default constructor should be either val or var.
// 3. Data class cannot be abstract, open, sealed or inner.

data class Beer(val name: String, val voAlcohol: Double, val price: Double)

// Data class in Kotlin properly construct a Canonical Methods (equals, hashCode, copy), that is declared at Any.
fun dataClassCanonicalMethodExample() {
    val budwiser = Beer("Budwiser", 4.5, 2.99)
    val lite = Beer("Lite", 5.0, 2.99)
    val budwiserFreedom = Beer("Budwiser", 4.5, 2.99)

    // equals in Kotlin compares a values.
    println(budwiser.equals(budwiserFreedom))

    // copy method.
    val liteGraffiti = lite.copy(name = "lite")

    // Data class deconstruction
    val (name, voAlcohol, price) = budwiser
    println("$name, $voAlcohol, $price")
}
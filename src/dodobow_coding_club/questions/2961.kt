package dodobow_coding_club.questions

import kotlin.math.abs

fun main() {
    val n = readln().toInt()
    val tastes = List(n) {
        readln().split(" ").map { it.toInt() }.run { Pair(this[0], this[1]) }
    }

    var differences = 999999999
    for (i in 1..n) {
        val combs = combination(tastes, i)
        for (comb in combs) {
            var sourness = 1        // Sourness -> Multiplication
            var bitterness = 0      // Bitterness -> Addition

            comb.forEach { (s, b) ->
                sourness *= s
                bitterness += b
            }
            differences = differences.coerceAtMost(abs(sourness - bitterness))
        }
    }
    println(differences)
}
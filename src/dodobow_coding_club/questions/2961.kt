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

fun combination(arr: List<Pair<Int, Int>>, n: Int): List<List<Pair<Int, Int>>> {
    // if n == 0, return list of emptyList (no possible combination remaining)
    if (n == 0) return listOf(emptyList())
    if (arr.isEmpty()) return emptyList()

    val result = mutableListOf<List<Pair<Int, Int>>>()
    for (i in arr.indices) {
        val selection = arr[i]
        val remaining = arr.drop(i + 1)     // .drop: return array after dropping first 'n' elements.
        for (c in combination(remaining, n - 1)) {
            result.add(listOf(selection) + c)       // keep stacking possible selections into a single list -> generate combinations.
        }
    }
    return result
}
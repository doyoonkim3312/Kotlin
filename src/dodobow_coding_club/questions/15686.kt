package dodobow_coding_club.questions

import kotlin.math.abs

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    // Location of Chicken Store
    val chickenStore = mutableListOf<Pair<Int, Int>>()
    val field = List<List<Int>>(n) { r ->
        readln().split(" ").mapIndexed { c, s ->
            s.toInt().also { if (it == 2) chickenStore.add(Pair(r, c)) }
        }
    }
    // Possible combination of chicken places in City
    val cases = combination(chickenStore, m).toSet()

    var shortest = 1000000000
    for (case in cases) {
        val temp = MutableList(n) { MutableList(n) { 0 } }
        for (r in field.indices) {
            for (c in field[r].indices) {
                if (field[r][c] == 1) {
                    var cDistance = 100
                    for (l in case) {
                        cDistance = cDistance.coerceAtMost(
                            abs(l.first - r) + abs(l.second - c)
                        )
                    }
                    temp[r][c] = cDistance
                }
            }
        }
        shortest = shortest.coerceAtMost(temp.flatten().sum())
    }
    println(shortest)
}